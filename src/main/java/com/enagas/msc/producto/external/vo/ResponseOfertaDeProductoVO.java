package com.enagas.msc.producto.external.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ResponseOfertaDeProductoVO {

	private BigDecimal precioMaxCompra;

	private Long capacidadMaxCompra;

	private BigDecimal precioMinVenta;

	private Long capacidadMinVenta;
}
