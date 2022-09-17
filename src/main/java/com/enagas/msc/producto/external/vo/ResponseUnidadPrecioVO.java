package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
@Data
@JsonPropertyOrder({ "idnUnidadPrecio", "codUnidadPrecio", "desUnidadPrecio", "idnServAtr", "idnTipProducto", "fecIniVigencia", "fecFinVigencia" })
@JsonInclude(Include.NON_NULL)
public class ResponseUnidadPrecioVO {

	@JsonProperty("idnUnidadPrecio")
	private Integer idnUnidadPrecio;

	@JsonProperty("codUnidadPrecio")
	private String codUnidadPrecio;

	@JsonProperty("desUnidadPrecio")
	private String desUnidadPrecio;
	
	@JsonProperty("idnServAtr")
	private Integer idnServAtr;
	
	@JsonProperty("idnTipProducto")
	private Integer idnTipProducto;
	
	@JsonProperty("fecIniVigencia")
	private LocalDateTime fecIniVigencia;
	
	@JsonProperty("fecFinVigencia")
	private LocalDateTime fecFinVigencia;

}
