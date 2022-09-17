package com.enagas.msc.producto.external.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idnUnidadMedida", "codUnidadMedida", "desUnidadMedida", "fecIniVigencia", "fecFinVigencia" })
@JsonInclude(Include.NON_NULL)
public class ResponseUnidadMedidaVO {

	@JsonProperty("idnUnidadMedida")
	private Integer idnUnidadMedida;

	@JsonProperty("codUnidadMedida")
	private String codUnidadMedida;

	@JsonProperty("desUnidadMedida")
	private String desUnidadMedida;
	
	@JsonProperty("fecIniVigencia")
	private LocalDateTime fecIniVigencia;
	
	@JsonProperty("fecFinVigencia")
	private LocalDateTime fecFinVigencia;

}
