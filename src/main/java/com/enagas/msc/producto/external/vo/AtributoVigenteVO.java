package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AtributoVigenteVO {
	
	@JsonProperty(value = "idAtributoVigencia")
	@NotNull
	private Integer idAtributoVigencia ;
	
	@JsonProperty(value = "idTipoAtributo")
	@NotNull
	private Integer idTipoAtributo;
	
	@JsonProperty(value = "valorAtributo")
	@NotNull
	private String valorAtributo;
	
	@JsonProperty(value = "fechaInicio")
	@NotNull
	private LocalDateTime fechaInicio;	
	
	@JsonProperty(value = "fechaFin")
	private LocalDateTime fechaFin;	
	
	@JsonProperty(value = "idInfr")
	private Integer idInfr;
	
	@JsonProperty(value = "idAgrupInfr")
	private Integer idAgrupInfr;
	
	@JsonProperty(value = "idPuntoConex")
	private Integer idPuntoConex;
}
