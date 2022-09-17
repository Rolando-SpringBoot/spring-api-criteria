package com.enagas.msc.producto.external.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * la clase ResponseServAtrAtribVigVO.
 */
@Data
@JsonPropertyOrder({ "idnServAtr", "valor" })
@JsonInclude(Include.NON_NULL)
public class ResponseServAtrAtribVigVO {

	/**
	 * el idnServAtr - Codigo interno secuencial e irrepetible de identificacion del
	 * elemento
	 */
	@JsonProperty("idnServAtr")
	private Integer idnServAtr;

	/**
	 * el valor - Valor que toma el atributo en la vigencia definida en el registro
	 */
	@JsonProperty("valor")
	private String valor;

}