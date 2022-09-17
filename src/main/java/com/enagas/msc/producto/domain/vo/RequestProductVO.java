package com.enagas.msc.producto.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RequestProductVO {
	
	private Integer idnProducto; 
	
	private String codProducto;
	
	@NotNull(message = "El campo tipo producto es obligatorio") 
	private Integer idnTipoProducto;
	
	@NotNull(message = "El campo servicio atr es obligatorio") 
	private Integer idnServAtr;
	
	private List<Integer> idnInfr;
	private List<Integer> idnAgrupInfr;
	private List<Integer> idnPuntoConex;
	
	@NotNull(message = "El campo fecha inicio de producto es obligatorio") 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecIniProducto;
	
	@NotNull(message = "El campo fecha fin de producto es obligatorio") 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecFinProducto;
	
	@NotNull(message = "El campo fecha de inicio de sesión negociación es obligatorio") 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecIniSesion;
	
	@NotNull(message = "El campo fecha de fin de sesión negociación es obligatorio") 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecFinSesion;
	
	private String audUsuario;
}
