package com.enagas.msc.producto.external.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * Clase RequestUnidadMedidaVO.
 * 
 * @version 1.0
 * @since 29/03/2022
 */
@Data
@JsonPropertyOrder({ "idnServAtr", "desServAtr" })
@JsonInclude(Include.NON_NULL)
public class ResponseServAtrVO {

	/**
	 * Codigo interno secuencial e irrepetible de identificacion del elemento
	 */
	@JsonProperty("key")
	private Integer idnServAtr;

	/**
	 * Descripción del servicio
	 */
	@JsonProperty("value")
	private String desServAtr;

}