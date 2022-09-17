package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The RequestProductoVO class.
 */
@Data
public class RequestProductoVO {

    /**
     * The idnProducto - Identificador interno del producto
     */
	 @JsonProperty(value = "idnProducto")
	 private Integer idnProducto;	 
	 
    /**
     * The codProducto - Código identificativo del producto
     */
	 @JsonProperty(value = "codProducto")
	 @NotEmpty(message = "Campo codProducto obligatorio")
	 private String codProducto;	 
	 
    /**
     * The idnTipoProducto - Tipo de Producto
     */
	 @JsonProperty(value = "idnTipoProducto")
	 @NotNull(message = "Campo idnTipoProducto obligatorio")
	 private Integer idnTipoProducto;	 
	 
    /**
     * The idnServAtr - Servicio al que se asocia el producto
     */
	 @JsonProperty(value = "idnServAtr")
	 @NotNull(message = "Campo idnServAtr obligatorio")
	 private Integer idnServAtr;	 
	 
    /**
     * The idnInfr - Identificador de la infraestructura
     */
	 @JsonProperty(value = "idnInfr")
	 @NotNull(message = "Campo idnInfr obligatorio")
	 private Integer idnInfr;	 
	 
    /**
     * The idnAgrupInfr - Agrupación de infraestructura
     */
	 @JsonProperty(value = "idnAgrupInfr")
	 @NotNull(message = "Campo idnAgrupInfr obligatorio")
	 private Integer idnAgrupInfr;	 
	 
    /**
     * The idnUnidadMedida - Unidad de medida
     */
	 @JsonProperty(value = "idnUnidadMedida")
	 @NotNull(message = "Campo idnUnidadMedida obligatorio")
	 private Integer idnUnidadMedida;	 
	 
	 /**
     * The idnPuntoConex - Punto de conexión
     */
	 @JsonProperty(value = "idnPuntoConex")
	 @NotNull(message = "Campo idnPuntoConex obligatorio")
	 private Integer idnPuntoConex;	 
	 
    /**
     * The fecIniProducto - Fecha de inicio del producto
     */
	 @JsonProperty(value = "fecIniProducto")
	 @NotNull(message = "Campo fecIniProducto obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniProducto;	 
	 
    /**
     * The fecFinProducto - Fecha de fin del producto
     */
	 @JsonProperty(value = "fecFinProducto")
	 @NotNull(message = "Campo fecFinProducto obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinProducto;	 
	 
    /**
     * The fecIniSesion - Fecha de inicio de sesión de negociación del producto
     */
	 @JsonProperty(value = "fecIniSesion")
	 @NotNull(message = "Campo fecIniSesion obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniSesion;	 
	 
    /**
     * The fecFinSesion - Fecha de fin de sesión de negociación del producto
     */
	 @JsonProperty(value = "fecFinSesion")
	 @NotNull(message = "Campo fecFinSesion obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinSesion;	 
	 
    /**
     * The numVersion - Versión del producto
     */
	 @JsonProperty(value = "numVersion")
	 @NotNull(message = "Campo numVersion obligatorio")
	 private java.math.BigDecimal numVersion;	 
	 
    /**
     * The fecVersion - Fecha de alta de la versión
     */
	 @JsonProperty(value = "fecVersion")
	 @NotNull(message = "Campo fecVersion obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecVersion;	 
	 
	@JsonProperty(value = "precioMinVenta")
	private java.math.BigDecimal precioMinVenta;

	@JsonProperty(value = "capacidadMinVenta")
	private Long capacidadMinVenta;

	@JsonProperty(value = "precioMaxCompra")
	private java.math.BigDecimal precioMaxCompra;

	@JsonProperty(value = "capacidadMaxCompra")
	private Long capacidadMaxCompra;
	 
    /**
     * The audUsuAlta - Se llena la primera vez cuando se realiza el insert
     */
	 @JsonProperty(value = "audUsuAlta")
	 @NotEmpty(message = "Campo audUsuAlta obligatorio")
	 private String audUsuAlta;	 
	 
    /**
     * The audFecAlta - Se llena la primera vez cuando se realiza el insert
     */
	 @JsonProperty(value = "audFecAlta")
	 @NotNull(message = "Campo audFecAlta obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime audFecAlta;	 
	 
    /**
     * The audUsuModi - Se llena cada vez que modificamos la fila
     */
	 @JsonProperty(value = "audUsuModi")
	 private String audUsuModi;	 
	 
    /**
     * The audFecModi - Se llena cada vez que modificamos la fila
     */
	 @JsonProperty(value = "audFecModi")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime audFecModi;	 
	 
    /**
     * The audFecBaja - Se llena cada vez que damos de baja la fila
     */
	 @JsonProperty(value = "audFecBaja")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime audFecBaja;	 
	 
}