package com.enagas.msc.producto.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.enagas.msc.producto.domain.Producto;
import com.enagas.msc.producto.domain.vo.RequestFiltrosObtieneProductoVO;
import com.enagas.msc.producto.service.vo.RequestFiltrosProductoVO;
import com.enagas.msc.producto.service.vo.RequestServicioFilterVO;
import com.enagas.msc.producto.utils.EnumConstants;

/**
 * Spring JPA repository interface for Producto entity.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	default List<Producto> findProductos(EntityManager em, RequestFiltrosObtieneProductoVO filtros) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> query = cb.createQuery(Producto.class);
		Root<Producto> tproductos = query.from(Producto.class);

		List<Predicate> condiciones = new ArrayList<>();

		// Importante: solo producto(s) activo(s)
		condiciones.add(cb.isNull(tproductos.get("audFecBaja")));

		/** COndicional exclusivo para consulta de historial de ofertas */
		if (filtros.getIdnListaProducto() != null) {
			Expression<Integer> idnProductoExpression = tproductos.get("idnProducto");
			condiciones.add(idnProductoExpression.in(filtros.getIdnListaProducto()));
		}

		/*
		 * Columnas cuyos valores, si vienen informados, se añaden a la consulta con
		 * EQUALS El tipado del valor no afecta salvo si es String y contiene
		 * asteriscos; en ese caso se usará LIKE
		 */
		Map<String, Object> properties = new HashMap<>();
		properties.put("idnProducto", filtros.getIdnProducto());
		properties.put("codProducto", filtros.getCodProducto());
		properties.put("idnTipoProducto", filtros.getIdnTipoProducto());
//        properties.put("idnServAtr", filtros.getIdnServAtr());
		properties.entrySet().stream().filter(e -> Objects.nonNull(e.getValue())).forEach(e -> {
			if (e.getValue() instanceof String text && text.contains("*")) {
				condiciones.add(cb.like(tproductos.get(e.getKey()), text.replace("*", "%")));
			} else {
				condiciones.add(cb.equal(tproductos.get(e.getKey()), e.getValue()));
			}
		});

		if (filtros.getIdnServAtr() != null && !filtros.getIdnServAtr().isEmpty()) {
			Expression<Integer> idnServAtrExpression = tproductos.get("idnServAtr");
			condiciones.add(idnServAtrExpression.in(filtros.getIdnServAtr()));
		}

		/*
		 * Columnas cuyos valores, si vienen informados, se añaden como colecciones a la
		 * consulta mediante IN En este caso se relacionan entre sí mediante un OR,
		 * antes de ir a la consulta general con un AND
		 */
		Map<String, List<Integer>> infrAtribLists = new HashMap<>();
		infrAtribLists.put("idnInfr", filtros.getIdnInfr());
		infrAtribLists.put("idnAgrupInfr", filtros.getIdnAgrupInfr());
		infrAtribLists.put("idnPuntoConex", filtros.getIdnPuntoConex());
		List<Predicate> nonNullPredicates = infrAtribLists.entrySet().stream()
				.filter(e -> !CollectionUtils.isEmpty(e.getValue())).map(e -> {
					List<Integer> nonNullValues = e.getValue().stream().filter(Objects::nonNull).toList();
					return nonNullValues.isEmpty() ? null : tproductos.get(e.getKey()).in(nonNullValues);
				}).filter(Objects::nonNull).toList();
		if (!CollectionUtils.isEmpty(nonNullPredicates)) {
			condiciones.add(cb.or(nonNullPredicates.toArray(new Predicate[0])));
		}

		// si la consulta es de productos futuros, se recuperarán aquellos con fecha de
		// negociación posterior a la actual
		// y fecha de fin de producto anterior al limite recuperado
		if (filtros.getFecLimiteProgramada() != null) {
			// todo fecha inicio negociación > sysdate
			// and fecha fin producto <= filtros.getfechalimiteprogramada
//			condiciones.add
//			cb.greater
			Optional.ofNullable(filtros.getFecLimiteProgramada())
					.ifPresent(value -> condiciones.add(cb.lessThanOrEqualTo(tproductos.get("fecFinProducto"), value)));
			Optional.ofNullable(LocalDateTime.now())
					.ifPresent(value -> condiciones.add(cb.greaterThan(tproductos.get("fecIniSesion"), value)));
		}
		// Ojo, se capturará cualquier producto cuya sesión se solape parcial o
		// totalmente con el periodo formado por valores de inicio y fin que
		// introduzcamos
		else {
			Predicate overlappingBetween = overlappingBetween(cb, tproductos, filtros.getFecIniSesion(),
					filtros.getFecFinSesion(), "fecIniSesion", "fecFinSesion");
			Optional.ofNullable(overlappingBetween).ifPresent(condiciones::add);
		}

		/*
		 * En el caso del periodo del producto, no se captura por solapamiento sino que
		 * se establecerán los valores de inicio y fin que introducimos como límites
		 * contenedores Si sólo se informa la fecha de inicio, se buscará desde ésta en
		 * adelante en el tiempo Si sólo se informa la fecha de fin, se buscará desde
		 * ésta hacia atrás en el tiempo Si se informan ambas, se buscará única y
		 * exclusivamente dentro del rango que comprenden las dos
		 */
		Optional.ofNullable(filtros.getFecIniProducto())
				.ifPresent(value -> condiciones.add(cb.greaterThanOrEqualTo(tproductos.get("fecIniProducto"), value)));
		Optional.ofNullable(filtros.getFecFinProducto())
				.ifPresent(value -> condiciones.add(cb.lessThanOrEqualTo(tproductos.get("fecFinProducto"), value)));

		query.where(condiciones.toArray(new Predicate[0]));

		query.orderBy(cb.asc(tproductos.get("fecFinSesion")));

		return em.createQuery(query).getResultList();

	}

	/**
	 * Devuelve una lista de productos cuando se detecta algún solapamiento entre el
	 * período de sesión del producto y el rango de fechas que establece el filtro.
	 * <br>
	 * Opcionalmente, el filtro también puede indicar el servicio sobre el que
	 * quiere se aplicar la búsqueda.
	 * 
	 * @param em     manager para la transacción del usuario
	 * @param filter filtro de búsqueda con un rango de fechas concreto y,
	 *               opcionalmente, un servicio asociado
	 * @return lista de productos afectados
	 */
	default List<Producto> findAffectedByService(EntityManager em, RequestServicioFilterVO filter) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> query = cb.createQuery(Producto.class);
		Root<Producto> tproductos = query.from(Producto.class);

		List<Predicate> clauses = new ArrayList<>();

		if (Objects.nonNull(filter.getIdnServAtr())) {
			clauses.add(cb.equal(tproductos.get("idnServAtr"), filter.getIdnServAtr()));
		}

		clauses.add(cb.isNull(tproductos.get("audFecBaja")));

		Predicate overlappingBetween = overlappingBetween(cb, tproductos, filter.getFecIniVigencia(),
				filter.getFecFinVigencia(), "fecIniProducto", "fecFinProducto");
		Optional.ofNullable(overlappingBetween).ifPresent(clauses::add);

		query.where(clauses.toArray(new Predicate[0]));

		return em.createQuery(query).getResultList();
	}

	/**
	 * Captura cualquier producto cuyo periodo o sesión (han de indicarse los
	 * nombres de las columnas como parámetros de entrada) se solape parcial o
	 * totalmente con el periodo formado por las fechas inicio y fin que
	 * introducimos.
	 * 
	 * <p>
	 * El periodo o sesión de un producto puede representarse mediante un segmento
	 * [a,b] donde <i>a</i> es fecIni y <i>b</i> es fecFin:
	 * 
	 * Producto: a -------------- b
	 * 
	 * <p>
	 * Si entendemos [x,y] como el rango de fechas del filtro, donde <i>x</i> es su
	 * fecha de inicio e <i>y</i> de fin
	 * 
	 * Filtro: x++++++++++++y
	 * 
	 * La casuística de solapamientos entre el producto y las fechas del filtro
	 * quedaría:<br>
	 * 
	 * Caso 1: x++++++++++++++++++++++++y Producto: a -------------- b Caso 2:
	 * x++++++y Caso 3: x++++++++y Caso 4: x+++++++y Caso 5: x+++++++y Caso 6:
	 * x+++++y
	 * 
	 * <ul>
	 * Los casos de arriba podrían agruparse en dos situaciones:
	 * <li>Solapamiento en la parte derecha del filtro: Recogería los casos 1 y 3.
	 * El caso 2 debe ser excluido al construirse la consulta</li>
	 * <li>Solapamiento en la parte izquierda del filtro: Recogería los casos 4 y 5.
	 * El caso 6 debe ser excluido al construirse la consulta</li>
	 * </ul>
	 * 
	 * @param cb              criteria builder
	 * @param table           tabla de la que parte la consulta
	 * @param startDate       fecha de inicio que suministramos para el periodo
	 *                        filtrante
	 * @param endDate         fecha de fin que suministramos para el periodo
	 *                        filtrante
	 * @param columnStartDate nombre de la columna de la fecha de inicio en el
	 *                        periodo o sesión del producto
	 * @param columnEndDate   nombre de la columna de la fecha de fin en el periodo
	 *                        o sesión del producto
	 * @return predicado correspondiente al solapamiento
	 */

	private Predicate overlappingBetween(CriteriaBuilder cb, Root<Producto> table, LocalDateTime startDate,
			LocalDateTime endDate, String columnStartDate, String columnEndDate) {
		if (Objects.isNull(startDate)) {
			return null;
		}

		// Solapamiento en la parte derecha del filtro
		Predicate lowerBound = cb.greaterThanOrEqualTo(table.get(columnStartDate), startDate);
		Predicate upperBound = cb.lessThanOrEqualTo(table.get(columnStartDate), endDate);
		Predicate startingApproach = Objects.isNull(endDate) ? lowerBound : cb.and(lowerBound, upperBound);

		// Solapamiento en la parte izquierda del filtro
		lowerBound = cb.lessThanOrEqualTo(table.get(columnStartDate), startDate);
		upperBound = cb.greaterThanOrEqualTo(table.get(columnEndDate), startDate);
		Predicate duringApproach = cb.and(lowerBound, upperBound);

		// Finalmente, tenemos en cuenta ambas posibilidades

		return cb.or(startingApproach, duringApproach);
	}

	public List<Producto> findByIdnProductoInAndAudFecBajaIsNull(List<Integer> ids);

	/**
	 * 
	 */
	default int updateProducto(Producto mscTproducto, EntityManager em) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Producto> cq = cb.createCriteriaUpdate(Producto.class);
		Root<Producto> tproductos = cq.from(Producto.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.equal(tproductos.get("idnProducto"), mscTproducto.getIdnProducto()));

		if (Objects.nonNull(mscTproducto.getFecIniProducto()))
			cq.set(tproductos.get("fecIniProducto"), mscTproducto.getFecIniProducto());

		if (Objects.nonNull(mscTproducto.getFecFinProducto()))
			cq.set(tproductos.get("fecFinProducto"), mscTproducto.getFecFinProducto());

		if (Objects.nonNull(mscTproducto.getFecIniSesion()))
			cq.set(tproductos.get("fecIniSesion"), mscTproducto.getFecIniSesion());

		if (Objects.nonNull(mscTproducto.getFecFinSesion()))
			cq.set(tproductos.get("fecFinSesion"), mscTproducto.getFecFinSesion());

		if (Objects.nonNull(mscTproducto.getMaxCapacidadCompra()))
			cq.set(tproductos.get("maxCapacidadCompra"), mscTproducto.getMaxCapacidadCompra());

		if (Objects.nonNull(mscTproducto.getMinCapacidadVenta()))
			cq.set(tproductos.get("minCapacidadVenta"), mscTproducto.getMinCapacidadVenta());

		if (Objects.nonNull(mscTproducto.getMaxPrecioCompra()))
			cq.set(tproductos.get("maxPrecioCompra"), mscTproducto.getMaxPrecioCompra());

		if (Objects.nonNull(mscTproducto.getMinPrecioVenta()))
			cq.set(tproductos.get("minPrecioVenta"), mscTproducto.getMinPrecioVenta());

		cq.set(tproductos.get("numVersion"), mscTproducto.getNumVersion());

		predicates.add(cb.and(cb.isNull(tproductos.get("audFecBaja"))));

		cq.where(predicates.toArray(new Predicate[0]));

		return em.createQuery(cq).executeUpdate();
	}

	default List<Producto> findProducto(EntityManager em, Producto producto) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> productoRoot = cq.from(Producto.class);

		List<Predicate> predicates = new ArrayList<>();

		Predicate fechaBajaIsnull = cb.isNull(productoRoot.get(EnumConstants.AUD_FECH_BAJA));
		predicates.add(cb.and(fechaBajaIsnull));

		Predicate predicateIdnTipoProducto = cb.equal(productoRoot.get(EnumConstants.ID_TIPO_PRODUCTO),
				producto.getIdnTipoProducto());
		predicates.add(cb.and(predicateIdnTipoProducto));

		Predicate predicateIdnSerAtr = cb.equal(productoRoot.get(EnumConstants.ID_SERV_ATR), producto.getIdnServAtr());
		predicates.add(cb.and(predicateIdnSerAtr));

		if (producto.getIdnAgrupInfr() != null) {
			Predicate predicate = cb.equal(productoRoot.get(EnumConstants.ID_AGRUP_INFR), producto.getIdnAgrupInfr());
			predicates.add(cb.and(predicate));
		}

		if (producto.getIdnInfr() != null) {
			Predicate predicate = cb.equal(productoRoot.get(EnumConstants.ID_INFR), producto.getIdnInfr());
			predicates.add(cb.and(predicate));
		}

		if (producto.getIdnPuntoConex() != null) {
			Predicate predicate = cb.equal(productoRoot.get(EnumConstants.ID_PUNTO_CONEX), producto.getIdnPuntoConex());
			predicates.add(cb.and(predicate));
		}

		Predicate predicateIniProducto = cb.equal(productoRoot.get(EnumConstants.FECH_INI_PRODUCTO),
				producto.getFecIniProducto());
		predicates.add(cb.and(predicateIniProducto));

		/*
		 * Predicate predicateFinProducto =
		 * cb.equal(productoRoot.get(EnumConstants.FECHA_FIN_PRODUCTO),
		 * producto.getFecFinProducto()); predicates.add(cb.and(predicateFinProducto));
		 */

		Predicate global = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		cq.where(global);

		TypedQuery<Producto> registrosCompletos = em.createQuery(cq);
		return registrosCompletos.getResultList();
	}

	default List<Producto> getProducts(EntityManager em, RequestFiltrosProductoVO requestFiltrosProductoVO) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> productoRoot = cq.from(Producto.class);

		List<Predicate> predicates = new ArrayList<>();

		Predicate fechaBajaIsnull = cb.isNull(productoRoot.get(EnumConstants.AUD_FECH_BAJA));
		predicates.add(cb.and(fechaBajaIsnull));

		if (requestFiltrosProductoVO.getCodProducto() != null) {
			Predicate predicate = cb.like(productoRoot.get(EnumConstants.COD_PRODUCTO),
					requestFiltrosProductoVO.getCodProducto().replace('*', '%'));
			predicates.add(predicate);
		}
		if (requestFiltrosProductoVO.getIdnTipoProducto() != null) {
			Predicate predicate = cb.equal(productoRoot.get(EnumConstants.ID_TIPO_PRODUCTO),
					requestFiltrosProductoVO.getIdnTipoProducto());
			predicates.add(predicate);
		}
		if (requestFiltrosProductoVO.getIdnAgrupInfr() != null
				&& !requestFiltrosProductoVO.getIdnAgrupInfr().isEmpty()) {
			Expression<Integer> expression = productoRoot.get(EnumConstants.ID_AGRUP_INFR);
			Predicate predicate = expression.in(requestFiltrosProductoVO.getIdnAgrupInfr());
			predicates.add(predicate);
		}
		if (requestFiltrosProductoVO.getIdnInfr() != null && !requestFiltrosProductoVO.getIdnInfr().isEmpty()) {
			Expression<Integer> expression = productoRoot.get(EnumConstants.ID_INFR);
			Predicate predicate = expression.in(requestFiltrosProductoVO.getIdnInfr());
			predicates.add(predicate);
		}
		if (requestFiltrosProductoVO.getIdnPuntoConex() != null
				&& !requestFiltrosProductoVO.getIdnPuntoConex().isEmpty()) {
			Expression<Integer> expression = productoRoot.get(EnumConstants.ID_PUNTO_CONEX);
			Predicate predicate = expression.in(requestFiltrosProductoVO.getIdnPuntoConex());
			predicates.add(predicate);
		}
		if (requestFiltrosProductoVO.getIdnServAtr() != null && !requestFiltrosProductoVO.getIdnServAtr().isEmpty()) {
			Expression<Integer> expression = productoRoot.get(EnumConstants.ID_SERV_ATR);
			Predicate predicate = expression.in(requestFiltrosProductoVO.getIdnServAtr());
			predicates.add(predicate);
		}

		if (requestFiltrosProductoVO.getFecIniProducto() != null
				&& requestFiltrosProductoVO.getFecFinProducto() == null) {
			Predicate predicate = cb.greaterThanOrEqualTo(productoRoot.get(EnumConstants.FECH_INI_PRODUCTO),
					requestFiltrosProductoVO.getFecIniProducto());
			predicates.add(predicate);
		}

		if (requestFiltrosProductoVO.getFecIniProducto() == null
				&& requestFiltrosProductoVO.getFecFinProducto() != null) {
			Predicate predicate2 = cb.lessThanOrEqualTo(productoRoot.get(EnumConstants.FECHA_FIN_PRODUCTO),
					requestFiltrosProductoVO.getFecFinProducto());
			predicates.add(predicate2);
		}

		if (requestFiltrosProductoVO.getFecIniProducto() != null
				&& requestFiltrosProductoVO.getFecFinProducto() != null) {
			Predicate predicate = cb.greaterThanOrEqualTo(productoRoot.get(EnumConstants.FECH_INI_PRODUCTO),
					requestFiltrosProductoVO.getFecIniProducto());
			predicates.add(predicate);
			Predicate predicate2 = cb.lessThanOrEqualTo(productoRoot.get(EnumConstants.FECHA_FIN_PRODUCTO),
					requestFiltrosProductoVO.getFecFinProducto());
			predicates.add(predicate2);
		}

		if (requestFiltrosProductoVO.getFecIniSesion() != null && requestFiltrosProductoVO.getFecFinSesion() == null) {
			Predicate predicate = cb.greaterThanOrEqualTo(productoRoot.get(EnumConstants.FECH_INI_SESION),
					requestFiltrosProductoVO.getFecIniSesion());
			predicates.add(predicate);
		}

		if (requestFiltrosProductoVO.getFecIniSesion() == null && requestFiltrosProductoVO.getFecFinSesion() != null) {
			Predicate predicate2 = cb.lessThanOrEqualTo(productoRoot.get(EnumConstants.FECH_FIN_SESION),
					requestFiltrosProductoVO.getFecFinSesion());
			predicates.add(predicate2);
		}

		if (requestFiltrosProductoVO.getFecIniSesion() != null && requestFiltrosProductoVO.getFecFinSesion() != null) {
			Predicate predicate = cb.greaterThanOrEqualTo(productoRoot.get(EnumConstants.FECH_INI_SESION),
					requestFiltrosProductoVO.getFecIniSesion());
			predicates.add(predicate);
			Predicate predicate2 = cb.lessThanOrEqualTo(productoRoot.get(EnumConstants.FECH_FIN_SESION),
					requestFiltrosProductoVO.getFecFinSesion());
			predicates.add(predicate2);
		}

		Predicate global = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		cq.where(global);
		cq.orderBy(cb.asc(productoRoot.get(EnumConstants.FECH_INI_PRODUCTO)),
				cb.asc(productoRoot.get(EnumConstants.AUD_FECH_ALTA)));

		TypedQuery<Producto> registrosCompletos = em.createQuery(cq);
		return registrosCompletos.getResultList();
	}

	default List<Producto> getProductsBySession(String period, EntityManager entityManager) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> root = cq.from(Producto.class);

		// mapa parametros
		Map<ParameterExpression<?>, Object> paramMap = new HashMap<>();

		// lista de predicados
		List<Predicate> predicateList = new ArrayList<>();

		Predicate predicateAduFecBajaIsNull = cb.isNull(root.get(EnumConstants.AUD_FECH_BAJA));
		predicateList.add(predicateAduFecBajaIsNull);

		if (Objects.nonNull(period) && !period.isBlank()) {

			ParameterExpression<String> parameterPatternDate = cb.parameter(String.class, EnumConstants.PATTERN_DATE);
			ParameterExpression<String> parameterPeriod = cb.parameter(String.class, EnumConstants.PERIOD);

			Expression<String> expressionFunctionToCharFecIniSesion = cb.function(EnumConstants.TO_CHAR, String.class,
					root.get(EnumConstants.FECH_INI_SESION), parameterPatternDate);
			Expression<String> expressionFunctionToCharFecFinSesion = cb.function(EnumConstants.TO_CHAR, String.class,
					root.get(EnumConstants.FECH_FIN_SESION), parameterPatternDate);

			Predicate predicateFecIniSesionEqualPeriod = cb.equal(expressionFunctionToCharFecIniSesion,
					parameterPeriod);
			Predicate predicateFecFinSesionEqualPeriod = cb.equal(expressionFunctionToCharFecFinSesion,
					parameterPeriod);
			Predicate predicatePeriodBetweenFecIniSesionAndFecFinSesion = cb.and(
					cb.lessThanOrEqualTo(expressionFunctionToCharFecIniSesion, parameterPeriod),
					cb.greaterThanOrEqualTo(expressionFunctionToCharFecFinSesion, parameterPeriod));

			Predicate predicateOrFecSesion = cb.or(predicateFecIniSesionEqualPeriod, predicateFecFinSesionEqualPeriod,
					predicatePeriodBetweenFecIniSesionAndFecFinSesion);

			predicateList.add(predicateOrFecSesion);

			paramMap.put(parameterPatternDate, "MMYYYY");
			paramMap.put(parameterPeriod, period);
		}

		cq.where(cb.and(predicateList.toArray(Predicate[]::new)));

		TypedQuery<Producto> tp = entityManager.createQuery(cq);

		paramMap.entrySet().forEach(e -> tp.setParameter(e.getKey().getName(), e.getValue()));

		return tp.getResultList();
	}

	default List<Producto> getProductsSession(EntityManager em, RequestFiltrosProductoVO requestFiltrosProductoVO) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
		Root<Producto> productoRoot = cq.from(Producto.class);

		List<Predicate> predicates = new ArrayList<>();

		Predicate fechaBajaIsnull = cb.isNull(productoRoot.get(EnumConstants.AUD_FECH_BAJA));
		predicates.add(cb.and(fechaBajaIsnull));

		if (requestFiltrosProductoVO.getFecIniSesion() != null && requestFiltrosProductoVO.getFecFinSesion() == null) {
			Predicate predicate = cb.lessThanOrEqualTo(productoRoot.get(EnumConstants.FECH_INI_SESION),
					requestFiltrosProductoVO.getFecIniSesion());
			Predicate predicate2 = cb.greaterThan(productoRoot.get(EnumConstants.FECH_FIN_SESION),
					requestFiltrosProductoVO.getFecIniSesion());
			predicates.add(predicate);
			predicates.add(predicate2);
		}

		Predicate global = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		cq.where(global);
		cq.orderBy(cb.asc(productoRoot.get(EnumConstants.FECH_INI_PRODUCTO)),
				cb.asc(productoRoot.get(EnumConstants.AUD_FECH_ALTA)));

		TypedQuery<Producto> registrosCompletos = em.createQuery(cq);
		return registrosCompletos.getResultList();
	}

}
