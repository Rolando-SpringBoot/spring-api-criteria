package com.enagas.msc.producto.external.service.impl;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.enagas.msc.producto.external.service.SujetoExternalRestService;
import com.enagas.msc.producto.external.vo.RequestValidarAccesoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class SujetoExternalRestServiceImpl implements SujetoExternalRestService {

	/** el Environment */
	private final Environment environment;

	/** el RestTemplate */
	private final RestTemplate restTemplate;

	@Override
	public Integer accessValidateEnabling(RequestValidarAccesoVO requestValidarAccesoVO) {
		log.info("Empieza metodo accessValidateEnabling :: Parametros [{}]", requestValidarAccesoVO);

		Integer response;

		String urlService = this.environment.getRequiredProperty("endpoint.service.enagas.msc.sujeto");
		String methodService = this.environment
				.getRequiredProperty("endpoint.method.post.accesoValidarHabilitacion");

		String endPoint = urlService.concat(methodService);

		// headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		try {
			response = this.restTemplate.exchange(endPoint, HttpMethod.POST,
					new HttpEntity<>(requestValidarAccesoVO, headers), Integer.class).getBody();
		} catch (RestClientException e) {
			return null;
		}

		log.info("Termina metodo accessValidateEnabling :: Result [{}]", response);

		return response;
	}

}
