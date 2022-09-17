package com.enagas.msc.producto.external.vo;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseCodigoEntidadVO {

	 /**
     * The idnInicio
     */
	 @JsonProperty(value = "idnInicio")
	 @NotEmpty
	 private Integer idnInicio;
	
	 /**
     * The idnFin
     */
	 @JsonProperty(value = "idnFin")
	 @NotEmpty
	 private Integer idnFin;
	
	 
}
