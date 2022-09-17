package com.enagas.msc.producto.external.service;

import java.time.LocalDateTime;
import java.util.List;

import com.enagas.msc.producto.external.vo.RequestCodigoEntidadVO;
import com.enagas.msc.producto.external.vo.ResponseCodigoEntidadVO;
import com.enagas.msc.producto.external.vo.ResponseEstadoEntidadVO;
import com.enagas.msc.producto.external.vo.ResponseServOfertadoVO;
import com.enagas.msc.producto.external.vo.ResponseUnidadPrecioVO;

public interface OperacionesAuxiliaresExternalRestService {
	ResponseCodigoEntidadVO getCodigoEntidad(RequestCodigoEntidadVO requestCodigoEntidadVO);
	
	List<ResponseEstadoEntidadVO> testadoentidads();
	
    ResponseUnidadPrecioVO getUnidadPrecio(Integer idnServAtr, String fecVigencia);
    List<ResponseUnidadPrecioVO> getUnidadPrecio(List<Integer> idsUnidadesPrecio);
    
    List<LocalDateTime> getDiaGas(LocalDateTime fecha);

    List<Integer> getTypeProductAssociatedServices(List<Integer> idnServAtr);
    
    Boolean isServiceSlot(List<Integer> idServAtr);
    
	List<ResponseServOfertadoVO> getServiciosOfertados();
}
