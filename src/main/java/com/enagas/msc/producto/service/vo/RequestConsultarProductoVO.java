package com.enagas.msc.producto.service.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The RequestConsultarProductoVO class.
 */
@Data
public class RequestConsultarProductoVO {

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
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniProducto;
	 
	/**
     * The fecFinProducto
     */
	 @JsonProperty(value = "fecFinProducto")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinProducto;
	 
	/**
     * The fecIniSesion
     */
	 @JsonProperty(value = "fecIniSesion")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniSesion;
	 
	/**
     * The fecFinSesion
     */
	 @JsonProperty(value = "fecFinSesion")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
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
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
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
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
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
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime audFecModi;
	 
	/**
     * The audFecBaja
     */
	 @JsonProperty(value = "audFecBaja")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime audFecBaja;
	 
}