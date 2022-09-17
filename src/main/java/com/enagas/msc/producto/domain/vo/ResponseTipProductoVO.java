package com.enagas.msc.producto.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * The ResponseTipProductoVO class.
 */
@Data
@JsonPropertyOrder({ "idnTipoProducto", "cod_tipo_producto", "desTipoProducto", "numOrden" })
@JsonInclude(Include.NON_NULL)
public class ResponseTipProductoVO {

	/**
	 * The idnTipoProducto - Identificador interno del tipo de producto
	 */
	@JsonProperty("key")
	private Integer idnTipoProducto;

	/**
	 * The codTipoProducto - Código identificativo del tipo de producto
	 */
	@JsonProperty("codTipoProducto")
	private String codTipoProducto;

	/**
	 * The desTipoProducto - Nombre del tipo de producto
	 */
	@JsonProperty("value")
	private String desTipoProducto;

	/**
	 * The numOrden - Número de orden del tipo producto
	 */
	@JsonProperty("numOrden")
	private Integer numOrden;

}
