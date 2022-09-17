package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Clase que representa un filtro de búsqueda para los Productos basado en un
 * rango de fechas que interfiere en algún momento con el de su sesión.
 * También, y de manera opcional, se puede indicar un servicio concreto.
 */
@Data
public class RequestFiltrarPorPeriodoVO {

	/** Identificador del servicio */
	private Integer idnServAtr;	 
	
	/**
	 * Fecha de inicio del rango
	 */
	@NotNull(message = "Field fecIniVigencia is mandatory") 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecIniVigencia;	 
	 
	/**
	 * Fecha de fin del rango (opcional). Si no se indica, el rango será de
	 * duración indeterminada
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecFinVigencia;
	 
}