package com.enagas.msc.producto.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enagas.msc.producto.domain.ProductoVer;
import com.enagas.msc.producto.utils.EnumConstants;

/**
 * Spring JPA repository interface for ProductoVer entity.
 */
@Repository
public interface ProductoVerRepository extends JpaRepository<ProductoVer, Integer> {
	
	default ProductoVer findUltimaById (Integer idnProducto, EntityManager em) {
		
		//Creamos los objetos necesarios para generar la query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		//Creamos los criterios de búsqueda
		CriteriaQuery<ProductoVer> cq = cb.createQuery(ProductoVer.class); 
		Root<ProductoVer> tBusqfiltro = cq.from(ProductoVer.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		Predicate predicate = cb.equal(tBusqfiltro.get(EnumConstants.ID_PRODUCTO), idnProducto);
		predicates.add(cb.and(predicate));
		
		Predicate predicateFecBaja = cb.isNull(tBusqfiltro.get(EnumConstants.AUD_FECH_BAJA));
		predicates.add(cb.and(predicateFecBaja));
		
		 
		cq.select(tBusqfiltro);
		cq.where(cb.and(predicates.toArray(new Predicate[] {})));
		Order ordenar = cb.desc(tBusqfiltro.get(EnumConstants.NUM_VERSION));
		cq.orderBy(ordenar);
		TypedQuery<ProductoVer> query = em.createQuery(cq);
				
		
		return query.getResultList().get(0);
	}

}