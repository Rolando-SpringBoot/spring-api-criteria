package com.enagas.msc.producto.domain.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Resultado de una búsqueda (sobre uno o más productos) identificable.
 * 
 * <p>Una búsqueda identificable es aquella que además de un Filtro de
 * búsqueda, lleva asociado un identificador único. Esto nos permite 
 * diferenciarla de otras así como poder gestionarlas mediante una colección
 * sin peligro de entremezclar sus resultados.
 *
 */
@Data
@AllArgsConstructor
public class ResponseIdentifiedFilterProductoVO {
	
	/**
	 * Identificador único de la búsqueda original que permitirá al
	 * solicitante de la operación asociar correctamente el resultado.
	 */
	@NotBlank
	private String idnFilter;
	
	/**
	 * Resultado con el listado de Productos obtenidos a partir del Filtro
	 * de búsqueda suministrado en la invocación.
	 */
	@Valid
	@NotEmpty
	private List<ResponseProductoVO> results;

}
