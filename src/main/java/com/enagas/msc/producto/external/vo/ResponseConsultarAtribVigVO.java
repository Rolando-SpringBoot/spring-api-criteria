package com.enagas.msc.producto.external.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResponseConsultarAtribVigVO {
	
	@JsonProperty(value = "idInfr")
	private Integer idInfr;	
	
	@JsonProperty(value = "idAgrupInfr")
	private Integer idAgrupInfr;
	
	@JsonProperty(value = "idPuntoConex")
	private Integer idPuntoConex;
	
	@JsonProperty(value = "desInstalacion")
	private String desInstalacion;
	
	@JsonProperty(value = "listaAtributos")
	private List<AtributoVigenteVO> listaAtributos ;
}
