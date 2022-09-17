package com.enagas.msc.producto.external.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestGenerarDescripcionInstalVO {
	
	@JsonProperty(value = "idnInfr")
	private List<Integer> listaIdInfr;
	
	@JsonProperty(value = "idnAgrupInfr")
	private List<Integer> listaIdAgrupInfr;
	
	@JsonProperty(value = "idnPuntoConex")
	private List<Integer> listaIdPuntoConex;

}
