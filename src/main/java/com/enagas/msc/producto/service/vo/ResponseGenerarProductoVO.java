package com.enagas.msc.producto.service.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The ResponseGenerarProductoVO class.
 */
@Data
public class ResponseGenerarProductoVO {

	/**
     * The idnProductoCalendario
     */
	 @JsonProperty(value = "idnProductoCalendario")
	 private Integer idnProductoCalendario;
	 
	/**
     * The codProductoCalendario
     */
	 @JsonProperty(value = "codProductoCalendario")
	 private String codProductoCalendario;
	 
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
     * The fecIniEstandar
     */
	 @JsonProperty(value = "fecIniEstandar")
	 private LocalDateTime fecIniEstandar;
	 
	/**
     * The fecFinEstandar
     */
	 @JsonProperty(value = "fecFinEstandar")
	 private LocalDateTime fecFinEstandar;
	 
	/**
     * The fecIniVigencia
     */
	 @JsonProperty(value = "fecIniVigencia")
	 private LocalDateTime fecIniVigencia;
	 
	/**
     * The fecFinVigencia
     */
	 @JsonProperty(value = "fecFinVigencia")
	 private LocalDateTime fecFinVigencia;
	 
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