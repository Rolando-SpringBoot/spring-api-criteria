package com.enagas.msc.producto.external.vo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestValidarAccesoVO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "idSujeto")
	private Integer idSujeto;

	@JsonProperty(value = "fechaInicio")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaInicio;

	@JsonProperty(value = "fechaFin")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fechaFin;

}
