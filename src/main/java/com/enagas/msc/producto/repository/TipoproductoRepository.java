package com.enagas.msc.producto.repository;

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
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import com.enagas.msc.producto.domain.TipProducto;
import com.enagas.msc.producto.utils.EnumConstants;


/**
 * Spring JPA repository interface for TipProducto entity.
 */
@Repository
public interface TipoproductoRepository extends JpaRepository<TipProducto, Integer> {

	/**
	 * Gets all TipProductos.
	 * 
	 * @param idTipoProductoList a list of TipProduct ids
	 * @param sort               the sort
	 * @param em                 the entity manager
	 * @return a list of TipProducto instances
	 */
	default List<TipProducto> obtenerTipoProducto(List<Integer> idTipoProductoList, Sort sort, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TipProducto> cq = cb.createQuery(TipProducto.class);
		Root<TipProducto> root = cq.from(TipProducto.class);

		// Lista que almacena las condiciones
		List<Predicate> predicateList = new ArrayList<>();

		// Mapa que almacena los parametros
		Map<ParameterExpression, Object> paramList = new HashMap<>();

		// Predicados
		Predicate predicateForFechaAudBajaNotNull = cb.isNull(root.get(EnumConstants.AUD_FECH_BAJA));
		predicateList.add(predicateForFechaAudBajaNotNull);

		if (Objects.nonNull(idTipoProductoList) && !idTipoProductoList.isEmpty()) {
			ParameterExpression<List> parameterIdTipoProductoList = cb.parameter(List.class, EnumConstants.ID_TIPO_PRODUCTO_LIST);
			Predicate predicateForInIdnTipoProducto = root.get(EnumConstants.ID_TIPO_PRODUCTO).in(parameterIdTipoProductoList);
			predicateList.add(predicateForInIdnTipoProducto);
			paramList.put(parameterIdTipoProductoList, idTipoProductoList);
		}

		cq.where(cb.and(predicateList.toArray(Predicate[]::new))).orderBy(QueryUtils.toOrders(sort, root, cb));

		TypedQuery<TipProducto> tq = em.createQuery(cq);

		for (Map.Entry<ParameterExpression, Object> entry : paramList.entrySet()) {
			if (entry.getKey().getName().equals(EnumConstants.ID_TIPO_PRODUCTO_LIST)) {
				tq.setParameter(entry.getKey(), (List<Integer>) entry.getValue());
			}
		}

		return tq.getResultList();
	}
	
	
	/**
	 * busca un tipo de producto a partir de un id, operación que permite recuperar
	 * un objeto correspondiente tipo de producto consultado.
	 * 
	 * @param id identificador del tipo de producto
	 * @return Tipo de Producto
	 */
	public Optional<TipProducto> findByIdnTipoProducto(Integer id);
	
	public TipProducto findByTxtAbrvProducto(String abreviatura);

}