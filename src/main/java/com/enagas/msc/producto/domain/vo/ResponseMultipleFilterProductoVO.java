package com.enagas.msc.producto.domain.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase que representa el resultado de ejecutar varias búsquedas de Productos,
 * de manera independiente pero a partir de una sola invocación/llamada.
 * 
 * <p>Para poder separar cada par búsqueda-resultado del resto, las búsquedas
 * contarán con un identificador único y un campo interno donde almacenar el 
 * resultado.
 */
@Data
@AllArgsConstructor
public class ResponseMultipleFilterProductoVO {
	
	/**
	 * Listado de resultados de varias búsquedas independientes
	 */
	private List<ResponseIdentifiedFilterProductoVO> items;

}
