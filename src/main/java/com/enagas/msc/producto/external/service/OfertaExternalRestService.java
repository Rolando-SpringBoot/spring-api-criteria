package com.enagas.msc.producto.external.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.enagas.msc.producto.external.vo.RequestConsultOffertsVO;
import com.enagas.msc.producto.external.vo.RequestGenerarDescripcionSujetoVO;
import com.enagas.msc.producto.external.vo.ResponseConsultOffertsVO;
import com.enagas.msc.producto.external.vo.ResponseConsultarOfertasVO;
import com.enagas.msc.producto.external.vo.ResponseOfertaDeProductoVO;
import com.enagas.msc.producto.external.vo.ResponseOfertaVO;
import com.enagas.msc.producto.external.vo.ResponseServOfertadoVO;

@Service
public interface OfertaExternalRestService {

	/**
	 * Recupera las ofertas del producto
	 *
	 * @param Integer idProducto
	 * @return ResponseOfertaDeProductoVO
	 */
	ResponseOfertaDeProductoVO consultarOfertaDeProducto(Integer idProducto);
	
	List<ResponseConsultOffertsVO> getConsultOnlyOffers(RequestConsultOffertsVO requestConsultOffertsVO);
	
	List<ResponseOfertaVO> getConsultOnlyOffersProducts(RequestConsultOffertsVO requestConsultOffertsVO);
	
	ResponseConsultarOfertasVO obtieneOfertas(Integer idnProducto, List<Integer> idnOfertaList,
		boolean indEstadoProducto, RequestGenerarDescripcionSujetoVO request);
	
	ResponseConsultarOfertasVO obtieneOfertasSlots(Integer idnProducto, List<Integer> idnOfertas, Integer idnSujetoLogado);

}
