package com.enagas.msc.producto.external.vo;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The ResponseMscTestadoEntidadVO class.
 */
@Data
public class ResponseEstadoEntidadVO {

    /**
     * The idnEstadoEntidad - Identificador interno del estado de entidad
     */
	 @JsonProperty(value = "idnEstadoEntidad")
	 @NotEmpty
	 private Integer idnEstadoEntidad;
	 
    /**
     * The codEstadoEntidad - Código identificativo del estado de entidad
     */
	 @JsonProperty(value = "codEstadoEntidad")
	 @NotEmpty
	 private String codEstadoEntidad;
	 
    /**
     * The desEstadoEntidad - Descripción del estado de entidad
     */
	 @JsonProperty(value = "desEstadoEntidad")
	 @NotEmpty
	 private String desEstadoEntidad;
	 
	 
//    /**
//     * The audUsuAlta - Se llena la primera vez cuando se realiza el insert
//     */
//	 @JsonProperty(value = "audUsuAlta")
//	 @NotEmpty
//	 private String audUsuAlta;
//	 
//    /**
//     * The audFecAlta - Se llena la primera vez cuando se realiza el insert
//     */
//	 @JsonProperty(value = "audFecAlta")
//	 @NotEmpty
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
//	 private java.sql.Timestamp audFecAlta;
//	 
//    /**
//     * The audUsuModi - Se llena cada vez que modificamos la fila
//     */
//	 @JsonProperty(value = "audUsuModi")
//	 private String audUsuModi;
//	 
//    /**
//     * The audFecModi - Se llena cada vez que modificamos la fila
//     */
//	 @JsonProperty(value = "audFecModi")
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
//	 private java.sql.Timestamp audFecModi;
//	 
//    /**
//     * The audFecBaja - Se llena cada vez que damos de baja la fila
//     */
//	 @JsonProperty(value = "audFecBaja")
//	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
//	 private java.sql.Timestamp audFecBaja;
	 
}