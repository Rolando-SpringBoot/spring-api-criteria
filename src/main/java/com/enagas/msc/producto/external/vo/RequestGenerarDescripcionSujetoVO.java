package com.enagas.msc.producto.external.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestGenerarDescripcionSujetoVO {

	@NotNull
	private List<Integer> listaSujeto;

	@NotNull
	private List<Integer> listaTipoSujeto;

	@NotNull
	private Boolean sujetosHabilitados;
}
