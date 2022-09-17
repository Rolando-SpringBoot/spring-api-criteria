package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The ResponseProductoCalendarioVO class.
 */
@Data
public class ResponseProductoCalendarioVO {

    /**
     * The idnProductoCalendario - Identificador del producto calendario
     */
	 @JsonProperty(value = "idnProductoCalendario")
	 @NotEmpty
	 private Integer idnProductoCalendario;
	 
    /**
     * The codProductoCalendario - Código identificativo del producto calendario
     */
	 @JsonProperty(value = "codProductoCalendario")
	 @NotEmpty
	 private String codProductoCalendario;
	 
    /**
     * The idnTipoProducto - Identificador del tipo de producto
     */
	 @JsonProperty(value = "idnTipoProducto")
	 @NotEmpty
	 private Integer idnTipoProducto;
	 
    /**
     * The idnServAtr - Identificador del servicio
     */
	 @JsonProperty(value = "idnServAtr")
	 @NotEmpty
	 private Integer idnServAtr;
	 
    /**
     * The fecIniEstandar - Fecha de inicio estándar para el Producto y Servicio
     */
	 @JsonProperty(value = "fecIniEstandar")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecIniEstandar;
	 
    /**
     * The fecFinEstandar - Fecha de Fin estándar para el Producto y Servicio
     */
	 @JsonProperty(value = "fecFinEstandar")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecFinEstandar;
	 
    /**
     * The fecIniVigencia - Fecha de Inicio del producto administrado
     */
	 @JsonProperty(value = "fecIniVigencia")
	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecIniVigencia;
	 
    /**
     * The fecFinVigencia - Fecha de Fin del producto administrado
     */
	 @JsonProperty(value = "fecFinVigencia")
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDateTime fecFinVigencia;
	 
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