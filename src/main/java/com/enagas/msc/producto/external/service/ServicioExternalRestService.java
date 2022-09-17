package com.enagas.msc.producto.external.service;

import java.util.List;

import com.enagas.msc.producto.external.vo.ResponseServAtrAtribVigVO;
import com.enagas.msc.producto.external.vo.ResponseServAtrVO;
import com.enagas.msc.producto.external.vo.ResponseServicioVO;
import com.enagas.msc.producto.external.vo.ResponseUnidadMedidaVO;


public interface ServicioExternalRestService {

	List<ResponseUnidadMedidaVO> getUnidadMedida(List<Integer> idsUnidadesMedida);
	
	ResponseServAtrAtribVigVO getSerAtribVig(Integer idnServAtr);
	
	ResponseServicioVO getServicioAtr(Integer idServAtr);
	
	List<ResponseServAtrVO> getServiciosAtr(List<Integer> listIdnServAtr);
}
