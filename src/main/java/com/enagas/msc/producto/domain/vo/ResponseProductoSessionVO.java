package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idnProducto", "codProducto", "idnTipoProducto", "fecIniProducto", "fecFinProducto", "idnServAtr",
		"idnInfr", "idnAgrupInfr", "idnPuntoConex", "idnUnidadMedida", "idnUnidadPrecio", "fecIniSesion", "fecFinSesion" })
public class ResponseProductoSessionVO {

	@JsonProperty("idnProducto")
	private Integer idnProducto;

	@JsonProperty("codProducto")
	private String codProducto;

	@JsonProperty("idnTipoProducto")
	private Integer idnTipoProducto;

	@JsonProperty("fecIniProducto")
	private LocalDateTime fecIniProducto;

	@JsonProperty("fecFinProducto")
	private LocalDateTime fecFinProducto;

	@JsonProperty("idnServAtr")
	private Integer idnServAtr;

	@JsonProperty("idnInfr")
	private Integer idnInfr;

	@JsonProperty("idnAgrupInfr")
	private Integer idnAgrupInfr;

	@JsonProperty("idnPuntoConex")
	private Integer idnPuntoConex;

	@JsonProperty("idnUnidadMedida")
	private Integer idnUnidadMedida;

	@JsonProperty("idnUnidadPrecio")
	private Integer idnUnidadPrecio;

	@JsonProperty("fecIniSesion")
	private LocalDateTime fecIniSesion;

	@JsonProperty("fecFinSesion")
	private LocalDateTime fecFinSesion;

}
