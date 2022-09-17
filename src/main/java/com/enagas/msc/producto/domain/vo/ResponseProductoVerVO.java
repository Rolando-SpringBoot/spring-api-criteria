package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The ResponseProductoVerVO class.
 */
@Data
public class ResponseProductoVerVO {

    /**
     * The idnProductoVer - Identificador interno de producto versión
     */
	 @JsonProperty(value = "idnProductoVer")
	 @NotEmpty
	 private Integer idnProductoVer;
	 
    /**
     * The codProducto - Código identificativo de producto versión
     */
	 @JsonProperty(value = "codProducto")
	 @NotEmpty
	 private String codProducto;
	 
    /**
     * The idnTipoProducto - Tipo de Producto
     */
	 @JsonProperty(value = "idnTipoProducto")
	 @NotEmpty
	 private Integer idnTipoProducto;
	 
    /**
     * The idnServAtr - Servicio al que se asocia el producto
     */
	 @JsonProperty(value = "idnServAtr")
	 @NotEmpty
	 private Integer idnServAtr;
	 
    /**
     * The idnInstalacion - Identificador de la instalación que puede ser infraestructura, agrupacion de infraestructura o punto de conexión
     */
	 @JsonProperty(value = "idnInstalacion")
	 @NotEmpty
	 private Integer idnInstalacion;
	 
    /**
     * The idnEstadoEntidad - Estado del producto
     */
	 @JsonProperty(value = "idnEstadoEntidad")
	 @NotEmpty
	 private Integer idnEstadoEntidad;
	 
    /**
     * The fecIniProducto - Fecha de inicio del producto
     */
	 @JsonProperty(value = "fecIniProducto")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecIniProducto;
	 
    /**
     * The fecFinProducto - Fecha de fin del producto
     */
	 @JsonProperty(value = "fecFinProducto")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecFinProducto;
	 
    /**
     * The fecIniSesion - Fecha de inicio de sesión de negociación del producto
     */
	 @JsonProperty(value = "fecIniSesion")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecIniSesion;
	 
    /**
     * The fecFinSesion - Fecha de fin de sesión de negociacion del producto
     */
	 @JsonProperty(value = "fecFinSesion")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecFinSesion;
	 
    /**
     * The numVersion - Versión del producto
     */
	 @JsonProperty(value = "numVersion")
	 @NotEmpty
	 private java.math.BigDecimal numVersion;
	 
    /**
     * The fecVersion - Fecha de alta de la versión
     */
	 @JsonProperty(value = "fecVersion")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecVersion;
	 
    /**
     * The idnProducto - Identificador del producto
     */
	 @JsonProperty(value = "idnProducto")
	 @NotEmpty
	 private ResponseProductoVO idnProducto;
	 
    /**
     * The audUsuAlta - Se llena la primera vez cuando se realiza el insert
     */
	 @JsonProperty(value = "audUsuAlta")
	 @NotEmpty
	 private String audUsuAlta;
	 
    /**
     * The audFecAlta - Se llena la primera vez cuando se realiza el insert
     */
	 @JsonProperty(value = "audFecAlta")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
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
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime audFecModi;
	 
    /**
     * The audFecBaja - Se llena cada vez que damos de baja la fila
     */
	 @JsonProperty(value = "audFecBaja")
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime audFecBaja;
	 
}