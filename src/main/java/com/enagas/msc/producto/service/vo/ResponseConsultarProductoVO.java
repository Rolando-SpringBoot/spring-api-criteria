package com.enagas.msc.producto.service.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The ResponseConsultarProductoVO class.
 */
@Data
public class ResponseConsultarProductoVO {

	/**
     * The idnProducto
     */
	 @JsonProperty(value = "idnProducto")
	 private Integer idnProducto;
	 
	/**
     * The codProducto
     */
	 @JsonProperty(value = "codProducto")
	 private String codProducto;
	 
	/**
     * The idnTipoProducto
     */
	 @JsonProperty(value = "idnTipoProducto")
	 private Integer idnTipoProducto;
	 
	/**
     * The idnServAtr
     */
	 @JsonProperty(value = "idnServAtr")
	 private Integer idnServAtr;
	 
	/**
     * The idnInstalacion
     */
	 @JsonProperty(value = "idnInstalacion")
	 private Integer idnInstalacion;
	 
	/**
     * The idnTipoInstalacion
     */
	 @JsonProperty(value = "idnTipoInstalacion")
	 private Integer idnTipoInstalacion;
	 
	/**
     * The idnEstadoEntidad
     */
	 @JsonProperty(value = "idnEstadoEntidad")
	 private Integer idnEstadoEntidad;
	 
	/**
     * The fecIniProducto
     */
	 @JsonProperty(value = "fecIniProducto")
	 private LocalDateTime fecIniProducto;
	 
	/**
     * The fecFinProducto
     */
	 @JsonProperty(value = "fecFinProducto")
	 private LocalDateTime fecFinProducto;
	 
	/**
     * The fecIniSesion
     */
	 @JsonProperty(value = "fecIniSesion")
	 private LocalDateTime fecIniSesion;
	 
	/**
     * The fecFinSesion
     */
	 @JsonProperty(value = "fecFinSesion")
	 private LocalDateTime fecFinSesion;
	 
	/**
     * The numVersion
     */
	 @JsonProperty(value = "numVersion")
	 private java.math.BigDecimal numVersion;
	 
	/**
     * The fecVersion
     */
	 @JsonProperty(value = "fecVersion")
	 private LocalDateTime fecVersion;
	 
	/**
     * The audUsuAlta
     */
	 @JsonProperty(value = "audUsuAlta")
	 private String audUsuAlta;
	 
	/**
     * The audFecAlta
     */
	 @JsonProperty(value = "audFecAlta")
	 private LocalDateTime audFecAlta;
	 
	/**
     * The audUsuModi
     */
	 @JsonProperty(value = "audUsuModi")
	 private String audUsuModi;
	 
	/**
     * The audFecModi
     */
	 @JsonProperty(value = "audFecModi")
	 private LocalDateTime audFecModi;
	 
	/**
     * The audFecBaja
     */
	 @JsonProperty(value = "audFecBaja")
	 private LocalDateTime audFecBaja;
	 
}