package com.enagas.msc.producto.external.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.enagas.msc.producto.external.service.OfertaExternalRestService;
import com.enagas.msc.producto.external.vo.RequestConsultOffertsVO;
import com.enagas.msc.producto.external.vo.RequestGenerarDescripcionSujetoVO;
import com.enagas.msc.producto.external.vo.ResponseConsultOffertsVO;
import com.enagas.msc.producto.external.vo.ResponseConsultarOfertasVO;
import com.enagas.msc.producto.external.vo.ResponseOfertaDeProductoVO;
import com.enagas.msc.producto.external.vo.ResponseOfertaVO;

@Service
public class OfertaExternalRestServiceImpl implements OfertaExternalRestService {

	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(OfertaExternalRestServiceImpl.class);

	private RestTemplate restTemplate;

	public OfertaExternalRestServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${endpoint.service.enagas.msc.oferta}")
	private String serviceMscOfertaCrudEndpoint;

	@Value("${endpoint.method.get.consultarCapacidadYCantidadDeProducto}")
	private String methodConsultarOfertaDeProducto;
	
	@Value("${endpoint.method.post.getConsultOnlyOffers}")
	private String methodgetConsultOnlyOffers;

	@Value("${endpoint.method.post.obtieneOfertas}")
	private String methodgetObtieneOfertas;
	
	@Value("${endpoint.method.get.obtiene-ofertas-slots}")
	private String methodgetObtieneOfertasSlots;

	
	/**
	 * Recupera las ofertas del producto
	 *
	 * @param Integer idProducto
	 * @return ResponseMscTofertaDeProductoVO
	 * @throws Exception 
	 */
	@Override
	public ResponseOfertaDeProductoVO consultarOfertaDeProducto(Integer idProducto) {

		
		log.info("Empieza método consultarOfertaDeProducto");
		

		String endPoint = serviceMscOfertaCrudEndpoint + methodConsultarOfertaDeProducto + "/" + idProducto;

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		

		return restTemplate.getForObject(endPoint, ResponseOfertaDeProductoVO.class);
	}
	
	@Override
	public List<ResponseConsultOffertsVO> getConsultOnlyOffers(RequestConsultOffertsVO requestConsultOffertsVO) {
		
		
		log.info("Empieza método getConsultOnlyOffers");
		
		
		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity<RequestConsultOffertsVO> request = new HttpEntity<>(requestConsultOffertsVO, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		
		log.info("Llamada al servicio REST externo [{}] ");
		
		
		return Arrays.asList(restTemplate.postForObject(serviceMscOfertaCrudEndpoint + methodgetConsultOnlyOffers, 
				request, ResponseConsultOffertsVO[].class));
	}

	@Override
	public ResponseConsultarOfertasVO obtieneOfertas(Integer idnProducto, List<Integer> idnOfertaList,
			boolean indEstadoProducto, RequestGenerarDescripcionSujetoVO requestGenerarDescripcionSujetoVO) {
		
		
		log.info("Empieza método obtieneOfertas");
		
		
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<RequestGenerarDescripcionSujetoVO> request = new HttpEntity<>(requestGenerarDescripcionSujetoVO, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseConsultarOfertasVO responseConsultarOfertasVO = new ResponseConsultarOfertasVO(); 
		try {
			responseConsultarOfertasVO = restTemplate.postForObject(serviceMscOfertaCrudEndpoint + methodgetObtieneOfertas + "?idnProducto=" + idnProducto +
				"&indEstadoProducto=" + indEstadoProducto, request, ResponseConsultarOfertasVO.class);
		}
		catch(Exception ex) {
			responseConsultarOfertasVO = null;
		}
		
		
		log.info("Llamada al servicio REST externo [{}] ");
		
		
		return responseConsultarOfertasVO;
	}

	@Override
	public ResponseConsultarOfertasVO obtieneOfertasSlots(Integer idnProducto, List<Integer> idnOfertas,
			Integer idnSujetoLogado) {
		
		
		log.info("Empieza método obtieneOfertasSlots");
		

		String endPoint = serviceMscOfertaCrudEndpoint + methodgetObtieneOfertasSlots + "?idnProducto=" + idnProducto + 
			"&idnSujetoLogado=" + idnSujetoLogado;

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		

		return restTemplate.getForObject(endPoint, ResponseConsultarOfertasVO.class);
	}

	@Override
	public List<ResponseOfertaVO> getConsultOnlyOffersProducts(RequestConsultOffertsVO requestConsultOffertsVO) {
		
		log.info("Empieza método getConsultOnlyOffers");
		
		
		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity<RequestConsultOffertsVO> request = new HttpEntity<>(requestConsultOffertsVO, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		
		log.info("Llamada al servicio REST externo [{}] ");
		
		
		return Arrays.asList(restTemplate.postForObject(serviceMscOfertaCrudEndpoint + methodgetConsultOnlyOffers, 
				request, ResponseOfertaVO[].class));
	}
}
