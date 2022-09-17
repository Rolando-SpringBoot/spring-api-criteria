package com.enagas.msc.producto.domain.vo;

import java.util.List;

import lombok.Data;

@Data
public class ResponseDeleteProductsVO {
	private String message;
	private List<ResponseDeleteCodes> products;
}
