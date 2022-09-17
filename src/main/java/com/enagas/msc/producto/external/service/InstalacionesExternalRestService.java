package com.enagas.msc.producto.external.service;

import java.util.List;

import com.enagas.msc.producto.external.vo.RequestConsultarAtribVigVO;
import com.enagas.msc.producto.external.vo.ResponseConsultarAtribVigVO;

public interface InstalacionesExternalRestService {
	
	List<ResponseConsultarAtribVigVO> getInstalacion(RequestConsultarAtribVigVO requestConsultarAtribVig);
	
}
