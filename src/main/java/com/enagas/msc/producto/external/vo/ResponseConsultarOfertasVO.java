package com.enagas.msc.producto.external.vo;

import java.util.List;

import lombok.Data;

@Data
public class ResponseConsultarOfertasVO {

	private List<ResponseOfertaVO> compra;
	
	private List<ResponseOfertaVO> venta;
}
