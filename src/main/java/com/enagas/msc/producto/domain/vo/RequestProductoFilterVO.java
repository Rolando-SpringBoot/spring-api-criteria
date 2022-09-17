package com.enagas.msc.producto.domain.vo;

import java.util.List;

import lombok.Data;

@Data
public class RequestProductoFilterVO {
	
	private List<RequestIdentifiedFilterProductoVO> filters;

}
