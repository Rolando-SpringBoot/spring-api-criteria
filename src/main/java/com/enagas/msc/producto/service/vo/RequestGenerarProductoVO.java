package com.enagas.msc.producto.service.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The RequestGenerarProductoVO class.
 */
@Data
public class RequestGenerarProductoVO {

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
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniEstandar;
	 
	/**
     * The fecFinEstandar
     */
	 @JsonProperty(value = "fecFinEstandar")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinEstandar;
	 
	/**
     * The fecIniVigencia
     */
	 @JsonProperty(value = "fecIniVigencia")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniVigencia;
	 
	/**
     * The fecFinVigencia
     */
	 @JsonProperty(value = "fecFinVigencia")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
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