package com.enagas.msc.producto.domain.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Clase que representa un listado de búsquedas independientes sobre
 * los Productos.<br>
 * Cada búsqueda cuenta con su propio Identificador y Filtro de búsqueda.
 * 
 * <p>La idea es evitar la repetición de llamadas externas a este 
 * microservicio cuando del otro lado se está trabajando con una colección
 * de objetos (por ejemplo, un listado de Ofertas de las que necesitamos
 * recuperar los Productos asociados).
 *
 */
@Data
public class RequestMultipleFilterProductoVO {
	
	/**
	 * Listado de búsquedas independientes
	 */
	@Valid
	@NotEmpty
	private List<RequestIdentifiedFilterProductoVO> items;

}
