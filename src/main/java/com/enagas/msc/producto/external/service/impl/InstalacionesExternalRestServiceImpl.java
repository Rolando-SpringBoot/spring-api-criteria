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

import com.enagas.msc.producto.external.service.InstalacionesExternalRestService;
import com.enagas.msc.producto.external.vo.DataComboInstalacion;
import com.enagas.msc.producto.external.vo.RequestConsultarAtribVigVO;
import com.enagas.msc.producto.external.vo.RequestGenerarDescripcionInstalVO;
import com.enagas.msc.producto.external.vo.ResponseConsultarAtribVigVO;

@Service
public class InstalacionesExternalRestServiceImpl implements InstalacionesExternalRestService {

	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ServicioExternalRestServiceImpl.class);

	private RestTemplate restTemplate;
	
	@Value("${endpoint.service.enagas.msc.instalacion}")
	private String instalacionEndPoint;

	@Value("${endpoint.method.post.consultarAtributoConVigencia}")
	private String methodPostInstalaciones;
	
	@Value("${endpoint.method.post.generarDescripcionInstalacion}")
	private String methodPostGenerarDescripcionInstalacion;
	
	public InstalacionesExternalRestServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	@Override
	public List<ResponseConsultarAtribVigVO> getInstalacion(RequestConsultarAtribVigVO requestConsultarAtribVig) {
		
		
		log.info("Empieza método getInstalacion");
		
		
		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity<RequestConsultarAtribVigVO> request = new HttpEntity<>(requestConsultarAtribVig, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		List<ResponseConsultarAtribVigVO> responseConsultarAtribVigVOS = Arrays.asList(restTemplate.postForObject(instalacionEndPoint + 
				methodPostInstalaciones, request, ResponseConsultarAtribVigVO[].class));

		
		log.info("Llamada al servicio REST externo [{}] ");
		

		return responseConsultarAtribVigVOS;
	}

}
