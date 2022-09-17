package com.enagas.msc.producto.domain.vo;

import java.util.List;

import lombok.Data;

@Data
public class ResponseDeleteCodes {
	private String codeProduct;
	private List<String> codeOffers;
}
