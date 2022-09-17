package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestConsultarAtribVigVO {

	@JsonProperty(value = "listaParmAtributo")
	@NotEmpty(message = "Campo idITipoInstalacion obligatorio")
	private List<String> listaParmAtributo;
	
	@JsonProperty(value = "listaIdInfr")
	private List<Integer> listaIdInfr;
	
	@JsonProperty(value = "listaIdAgrupIfr")
	private List<Integer> listaIdAgrupIfr;
	
	@JsonProperty(value = "listaIdPuntoConex")
	private List<Integer> listaIdPuntoConex;
	
	@JsonProperty(value = "fechaInicio")
	private LocalDateTime fechaInicio;
	
	@JsonProperty(value = "fechaFin")
	private LocalDateTime fechaFin;
}
