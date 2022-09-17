package com.enagas.msc.producto.external.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * la clase ResponseServAtrVO.
 */
@Data
@JsonPropertyOrder({ "idnServAtr", "uniServicio" })
@JsonInclude(Include.NON_NULL)
public class ResponseServicioVO {

	/**
	 * el idnServAtr - Codigo interno secuencial e irrepetible de identificacion del elemento
	 */
	@JsonProperty("idnServAtr")
	private Integer idnServAtr;

	/**
	 * el desServAtr - DESCRIPCION DEL SERVICIO
	 */
	@JsonProperty("uniServicio")
	private Integer uniServicio;

}

