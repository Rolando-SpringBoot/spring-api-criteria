package com.enagas.msc.producto.external.service;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

import com.enagas.msc.producto.external.vo.RequestAdminParamVO;


public interface AdminParamExternalService {
	
	/**
	 * Servicio para recupara los datos de los precios maximos con y sin prima.
	 * @author mdesantb
	 * @param requestConsultaParametroTipoAdminVo
	 * @return Map<String, String>
	 */
	public Map<String, String> consultarParametroTipoAdmin(RequestAdminParamVO requestConsultaParametroTipoAdminVo, RestTemplate restTemplate);
	

}
