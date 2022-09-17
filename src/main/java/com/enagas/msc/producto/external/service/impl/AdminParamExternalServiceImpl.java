package com.enagas.msc.producto.external.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.enagas.msc.producto.external.service.AdminParamExternalService;
import com.enagas.msc.producto.external.vo.RequestAdminParamVO;


@Service
public class AdminParamExternalServiceImpl implements AdminParamExternalService {
	
	 //Llamada al microservicio de operaciones auxiliares
	@Value("${endpoint.service.enagas.msc.operacionesaux}")
	private String URL_SERVICE_ADMIN_PARAM;
    @Value("${endpoint.method.post.consultarParametroTipoAdmin}")
	private String SERVICO_CONSULTA_PARAM_TIPO_ADMIN;


	/**
	 * Servicio para recupara los datos de los precios maximos con y sin prima.
	 * @author mdesantb
	 * @param requestConsultaParametroTipoAdminVo
	 * @return Map<String, String>
	 */
	public Map<String, String> consultarParametroTipoAdmin(RequestAdminParamVO requestConsultaParametroTipoAdminVo, RestTemplate restTemplate){
		
		//Se realiza una llamada al servicio de compruebaHoraMercado()
		//Falta crear el microservicio OperacionesAuxiliares		
        HttpHeaders headersParamTipoAdmin = new HttpHeaders();		
		HttpEntity<RequestAdminParamVO> requestInstalacion = new HttpEntity<>(requestConsultaParametroTipoAdminVo, headersParamTipoAdmin);
		headersParamTipoAdmin.setContentType(MediaType.APPLICATION_JSON);  		
		Map<String, String> responseOperacionesAux = null;	
		try {		
			responseOperacionesAux = restTemplate.postForObject(URL_SERVICE_ADMIN_PARAM+SERVICO_CONSULTA_PARAM_TIPO_ADMIN,requestInstalacion, HashMap.class);
		}catch(HttpClientErrorException ex){ //Cuando se recibe algún error 4xx
            ex.printStackTrace();
        }catch(HttpServerErrorException ex){ //Cuando se recibe algún error 5xx
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
		return responseOperacionesAux;
	}
	
	

}
