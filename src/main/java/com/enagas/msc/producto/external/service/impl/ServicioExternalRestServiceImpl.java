package com.enagas.msc.producto.external.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.enagas.msc.producto.external.service.ServicioExternalRestService;
import com.enagas.msc.producto.external.vo.ResponseServAtrAtribVigVO;
import com.enagas.msc.producto.external.vo.ResponseServAtrVO;
import com.enagas.msc.producto.external.vo.ResponseServicioVO;
import com.enagas.msc.producto.external.vo.ResponseUnidadMedidaVO;
import com.enagas.msc.producto.external.vo.ResponseUnidadPrecioVO;

@Service
public class ServicioExternalRestServiceImpl implements ServicioExternalRestService {

	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ServicioExternalRestServiceImpl.class);

	private RestTemplate restTemplate;

	public ServicioExternalRestServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${endpoint.service.enagas.msc.servicio}")
	private String serviceMscServicioCrudEndpoint;

	@Value("${endpoint.method.get.tunidadmedida.ids}")
	private String methodgetMscTunidadMedida;
	
	@Value("${endpoint.method.get.tservicioatratribvigs}")
	private String methodgetMscTservAtrAtribVig;
	
	@Value("${endpoint.method.get.tservicioatrs}")
	private String methodgetMscTservAtrs;
	
	@Value("${endpoint.method.get.tserviciosatrs}")
	private String methodgetMscTserviciosAtr;

	/**
	 * Recupera las ofertas del producto
	 *
	 * @param Integer idProducto
	 * @return ResponseMscTofertaDeProductoVO
	 * @throws Exception
	 */
	@Override
	public List<ResponseUnidadMedidaVO> getUnidadMedida(List<Integer> idsUnidadesMedida) {

		
		log.info("Empieza método consultarOfertaDeProducto");
		

		String endPoint = serviceMscServicioCrudEndpoint + methodgetMscTunidadMedida + "/?listaId="
				+ idsUnidadesMedida.toString().replace("[", "").replace("]", "");

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		

		return Arrays.asList(restTemplate.getForObject(endPoint, ResponseUnidadMedidaVO[].class));
	}
	
	@Override
	public ResponseServAtrAtribVigVO getSerAtribVig(Integer idnServAtr) {
		
		log.info("Empieza método getServicio");
		

		String endPoint = serviceMscServicioCrudEndpoint + methodgetMscTservAtrAtribVig + "?ids="+ idnServAtr;
		List<ResponseServAtrAtribVigVO> responseServAtrAtribVigVOS = Arrays.asList(restTemplate.getForObject(endPoint, ResponseServAtrAtribVigVO[].class));

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		
		
		return responseServAtrAtribVigVOS.get(0);
	}

	@Override
	public ResponseServicioVO getServicioAtr(Integer idServAtr) {
		
		log.info("Empieza método getServicioAtr");
		
		
		String endPoint = serviceMscServicioCrudEndpoint + methodgetMscTservAtrs + idServAtr;
		ResponseServicioVO responseServicioVO = restTemplate.getForObject(endPoint, ResponseServicioVO.class);

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		
		return responseServicioVO;
	}

	@Override
	public List<ResponseServAtrVO> getServiciosAtr(List<Integer> listIdnServAtr) {
		
		log.info("Empieza método getServiciosAtr");
		

		String endPoint = serviceMscServicioCrudEndpoint + methodgetMscTserviciosAtr + "?listIdnServAtr=" + 
				listIdnServAtr.toString().replace("[", "").replace("]", "");
		List<ResponseServAtrVO> responseServAtrVOS = Arrays.asList(restTemplate.getForObject(endPoint, ResponseServAtrVO[].class));

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		
		
		return responseServAtrVOS;
	}

}
