package com.enagas.msc.producto.external.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestCodigoEntidadVO {

	/**
     * The codEntidad
     */
	 @JsonProperty(value = "codEntidad")
	 private String codEntidad;	
	 
	 /**
     * The numEntidades
     */
	 @JsonProperty(value = "numEntidades")
	 private Integer numEntidades;	
	
}
