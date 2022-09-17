package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The ResponseProductoVO class.
 */
@Data
public class ResponseProductoVO {
	@JsonProperty(value = "idnProducto")
	@NotEmpty	private Integer idnProducto;

	@JsonProperty(value = "codProducto")
	@NotEmpty
	private String codProducto;

	@JsonProperty(value = "idnTipoProducto")
	@NotEmpty
	private Integer idnTipoProducto;

	
	@JsonProperty(value = "indEstadoProducto")
	@NotEmpty
	private Boolean indEstadoProducto;
	
	
	@JsonProperty(value = "fecIniProducto")
	@NotEmpty
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fecIniProducto;
	

	
	
	@JsonProperty(value = "fecFinProducto")
	@NotEmpty
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fecFinProducto;
	

	@JsonProperty(value = "idnServAtr")
	@NotEmpty
	private Integer idnServAtr;

	@JsonProperty(value = "idnInfr")
	@NotEmpty
	private Integer idnInfr;

	@JsonProperty(value = "idnAgrupInfr")
	@NotEmpty
	private Integer idnAgrupInfr;

	@JsonProperty(value = "idnPuntoConex")
	@NotEmpty
	private Integer idnPuntoConex;

	@JsonProperty(value = "idnUnidadMedida")
	@NotEmpty
	private Integer idnUnidadMedida;
	
	@JsonProperty(value = "unidadMedida")
	@NotEmpty
	private String unidadMedida;
	
	@JsonProperty(value= "idnUnidadPrecio")
	@NotEmpty
	private Integer idnUnidadPrecio;
	
	@JsonProperty(value= "unidadPrecio")
	@NotEmpty
	private String unidadPrecio;

	@JsonProperty(value = "fecIniSesion")
	@NotEmpty
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fecIniSesion;

	
	
	@JsonProperty(value = "fecFinSesion")
	@NotEmpty
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")

	private LocalDateTime fecFinSesion;

	@JsonProperty(value = "precioMinVenta")
	@NotEmpty
	private java.math.BigDecimal precioMinVenta;

	@JsonProperty(value = "capacidadMinVenta")
	@NotEmpty
	private Long capacidadMinVenta;

	@JsonProperty(value = "precioMaxCompra")
	@NotEmpty
	private java.math.BigDecimal precioMaxCompra;

	@JsonProperty(value = "capacidadMaxCompra")
	@NotEmpty
	private Long capacidadMaxCompra;
	
	@JsonProperty(value = "audFecAlta")
	private LocalDateTime audFecAlta;
	
	@JsonProperty(value = "desServAtr")
	@NotEmpty
	private String desServAtr;
	
	@JsonProperty(value = "desInstalacion")
	@NotEmpty
	private String desInstalacion;
}
