package com.enagas.msc.producto.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enagas.msc.producto.domain.ProductoCalendario;
import com.enagas.msc.producto.utils.EnumConstants;

/**
 * Spring JPA repository interface for ProductoCalendario entity.
 */
@Repository
public interface ProductoCalendarioRepository extends JpaRepository<ProductoCalendario, Integer> {
	
	default List<ProductoCalendario> validateProductDate (EntityManager em, LocalDateTime iniProducto, LocalDateTime finProducto,
			Integer idnTipoProducto, Integer idnSerAtr){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProductoCalendario> cq = cb.createQuery(ProductoCalendario.class);
		Root<ProductoCalendario> productoRoot = cq.from(ProductoCalendario.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		Predicate fechaBajaIsnull = cb.isNull(productoRoot.get(EnumConstants.AUD_FECH_BAJA));
		predicates.add(cb.and(fechaBajaIsnull));
		
		Predicate predicateIdnTipoProducto = cb.equal(productoRoot.get(EnumConstants.ID_TIPO_PRODUCTO), idnTipoProducto);
		predicates.add(cb.and(predicateIdnTipoProducto));
		
		Predicate predicateIdnSerAtr = cb.equal(productoRoot.get(EnumConstants.ID_SERV_ATR), idnSerAtr);
		predicates.add(cb.and(predicateIdnSerAtr));
		
		if(iniProducto != null && finProducto == null) {
			Predicate predicateDay = cb.equal(cb.function("DAY", Integer.class, productoRoot.get(EnumConstants.FECH_INI_ESTANDAR)), iniProducto.getDayOfMonth());
			Predicate predicateMonth = cb.equal(cb.function("MONTH", Integer.class, productoRoot.get(EnumConstants.FECH_INI_ESTANDAR)), iniProducto.getMonthValue());
			Predicate predicateYear = cb.lessThanOrEqualTo(cb.function("YEAR", Integer.class, productoRoot.get(EnumConstants.FECH_INI_ESTANDAR)), iniProducto.getYear()); 
			predicates.add(cb.and(predicateDay));
			predicates.add(cb.and(predicateMonth));
			predicates.add(cb.and(predicateYear));
		}
		
		if(iniProducto == null && finProducto != null) {
			Predicate predicateDay = cb.equal(cb.function("DAY", Integer.class, productoRoot.get(EnumConstants.FECH_FIN_ESTANDAR)), finProducto.getDayOfMonth());
			Predicate predicateMonth = cb.equal(cb.function("MONTH", Integer.class, productoRoot.get(EnumConstants.FECH_FIN_ESTANDAR)), finProducto.getMonthValue());
			Predicate predicateYear = cb.lessThanOrEqualTo(cb.function("YEAR", Integer.class, productoRoot.get(EnumConstants.FECH_FIN_ESTANDAR)), finProducto.getYear()); 
			predicates.add(cb.and(predicateDay));
			predicates.add(cb.and(predicateMonth));
			predicates.add(cb.and(predicateYear));
		}
		
		if(iniProducto != null && finProducto != null) {
			Predicate predicateDay = cb.equal(cb.function("DAY", Integer.class, productoRoot.get(EnumConstants.FECH_INI_ESTANDAR)), iniProducto.getDayOfMonth());
			Predicate predicateMonth = cb.equal(cb.function("MONTH", Integer.class, productoRoot.get(EnumConstants.FECH_INI_ESTANDAR)), iniProducto.getMonthValue());
			Predicate predicateYear = cb.lessThanOrEqualTo(cb.function("YEAR", Integer.class, productoRoot.get(EnumConstants.FECH_INI_ESTANDAR)), iniProducto.getYear()); 
			predicates.add(cb.and(predicateDay));
			predicates.add(cb.and(predicateMonth));
			predicates.add(cb.and(predicateYear));
			
			Predicate predicateDay2 = cb.equal(cb.function("DAY", Integer.class, productoRoot.get(EnumConstants.FECH_FIN_ESTANDAR)), finProducto.getDayOfMonth());
			Predicate predicateMonth2 = cb.equal(cb.function("MONTH", Integer.class, productoRoot.get(EnumConstants.FECH_FIN_ESTANDAR)), finProducto.getMonthValue());
			Predicate predicateYear2 = cb.lessThanOrEqualTo(cb.function("YEAR", Integer.class, productoRoot.get(EnumConstants.FECH_FIN_ESTANDAR)), finProducto.getYear()); 
			predicates.add(cb.and(predicateDay2));
			predicates.add(cb.and(predicateMonth2));
			predicates.add(cb.and(predicateYear2));
		}
		
		Predicate global = cb.and(predicates.toArray(new Predicate[predicates.size()]));
		cq.where(global);
		
		em.close();
		
		TypedQuery<ProductoCalendario> registrosCompletos = em.createQuery(cq);
		return registrosCompletos.getResultList();
	}
	
}