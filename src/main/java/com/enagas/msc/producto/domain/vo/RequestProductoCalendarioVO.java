package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The RequestProductoCalendarioVO class.
 */
@Data
public class RequestProductoCalendarioVO {

    /**
     * The idnProductoCalendario - Identificador del producto calendario
     */
	 @JsonProperty(value = "idnProductoCalendario")
	 private Integer idnProductoCalendario;	 
	 
    /**
     * The codProductoCalendario - Código identificativo del producto calendario
     */
	 @JsonProperty(value = "codProductoCalendario")
	 @NotEmpty(message = "Campo codProductoCalendario obligatorio")
	 private String codProductoCalendario;	 
	 
    /**
     * The idnTipoProducto - Identificador del tipo de producto
     */
	 @JsonProperty(value = "idnTipoProducto")
	 @NotNull(message = "Campo idnTipoProducto obligatorio")
	 private Integer idnTipoProducto;	 
	 
    /**
     * The idnServAtr - Identificador del servicio
     */
	 @JsonProperty(value = "idnServAtr")
	 @NotNull(message = "Campo idnServAtr obligatorio")
	 private Integer idnServAtr;	 
	 
    /**
     * The fecIniEstandar - Fecha de inicio estándar para el Producto y Servicio
     */
	 @JsonProperty(value = "fecIniEstandar")
	 @NotNull(message = "Campo fecIniEstandar obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniEstandar;	 
	 
    /**
     * The fecFinEstandar - Fecha de Fin estándar para el Producto y Servicio
     */
	 @JsonProperty(value = "fecFinEstandar")
	 @NotNull(message = "Campo fecFinEstandar obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinEstandar;	 
	 
    /**
     * The fecIniVigencia - Fecha de Inicio del producto administrado
     */
	 @JsonProperty(value = "fecIniVigencia")
	 @NotNull(message = "Campo fecIniVigencia obligatorio")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecIniVigencia;	 
	 
    /**
     * The fecFinVigencia - Fecha de Fin del producto administrado
     */
	 @JsonProperty(value = "fecFinVigencia")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	 private LocalDateTime fecFinVigencia;	 
	 
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