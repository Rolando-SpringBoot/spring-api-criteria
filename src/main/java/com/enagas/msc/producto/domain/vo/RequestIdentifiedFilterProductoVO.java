package com.enagas.msc.producto.domain.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Representa una búsqueda (sobre uno o más Productos) identificable.
 * 
 * <p>Esta clase está pensada para la solicitud de varias búsquedas
 * independientes desde una única llamada externa, pudiendo ser así fácilmente
 * gestionado mediante una colección.
 */
@Data
public class RequestIdentifiedFilterProductoVO {
	
	/**
	 * Identificador único de la búsqueda que permitirá asociarle después el
	 * resultado, para distinguirlo unívocamente del resto. 
	 */
	@NotEmpty
	private String idnFilter;
	
	/**
	 * Filtro de búsqueda.
	 */
	@Valid
	@NotNull
	private RequestFiltrosObtieneProductoVO filter;

}
