package com.enagas.msc.producto.external.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.enagas.msc.producto.external.service.OperacionesAuxiliaresExternalRestService;
import com.enagas.msc.producto.external.vo.RequestCodigoEntidadVO;
import com.enagas.msc.producto.external.vo.ResponseCodigoEntidadVO;
import com.enagas.msc.producto.external.vo.ResponseEstadoEntidadVO;
import com.enagas.msc.producto.external.vo.ResponseServOfertadoVO;
import com.enagas.msc.producto.external.vo.ResponseUnidadPrecioVO;

@Service
public class OperacionesAuxiliaresExternalRestServiceImpl implements OperacionesAuxiliaresExternalRestService{
	
	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ServicioExternalRestServiceImpl.class);
	
	@Value("${endpoint.service.enagas.msc.operacionesauxiliares}")
	private String operacionesAuxiliaresEndPoint;

	@Value("${endpoint.method.post.obtieneCodigoEntidad}")
	private String methodPostObtieneCodigoEntidad;
	
	@Value("${endpoint.method.get.testadoentidads}")
	private String methodGetTestadoentidads;
	
	@Value("${endpoint.method.get.getUnidadPrecio}")
	private String methodGetServatrfecvig;
	
	@Value("${endpoint.method.get.tunidadprecio.ids}")
	private String methodgetUnidadPrecio;
	
	@Value("${endpoint.method.get.typeProductAssociatedServices}")
	private String methodgetTypeProductAssociatedServices;
	
	@Value("${endpoint.method.get.isServiceSlot}")
	private String methodgetIsServiceSlot;

	@Value("${endpoint.method.get.getDiaGas}")
	private String methodGetGetDiaGas;
	
	@Value("${endpoint.method.get.tservofertado}")
	private String methodGetTservofertado;
	
	private RestTemplate restTemplate;
	
	public OperacionesAuxiliaresExternalRestServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public ResponseCodigoEntidadVO getCodigoEntidad(RequestCodigoEntidadVO requestCodigoEntidadVO) {
		
		log.info("Empieza método getInstalacion");
		
		HttpHeaders headers = new HttpHeaders();
		
		HttpEntity<RequestCodigoEntidadVO> request = new HttpEntity<>(requestCodigoEntidadVO, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return restTemplate.postForObject(operacionesAuxiliaresEndPoint + methodPostObtieneCodigoEntidad, 
				request, ResponseCodigoEntidadVO.class);
	}

	@Override
	public List<ResponseEstadoEntidadVO> testadoentidads() {
		
		log.info("Empieza método testadoentidads");
		
		
		return Arrays.asList(restTemplate.getForObject(operacionesAuxiliaresEndPoint + methodGetTestadoentidads, 
				ResponseEstadoEntidadVO[].class));
	}
	
	@Override
	public ResponseUnidadPrecioVO getUnidadPrecio(Integer idnServAtr, String fecVigencia) {
		
		log.info("Empieza método getUnidadPrecio");
		
    
		return restTemplate.getForObject(operacionesAuxiliaresEndPoint + methodGetServatrfecvig + 
				"?idnServAtr=" + idnServAtr + "&fecVigencia=" + fecVigencia,
				ResponseUnidadPrecioVO.class);
	}

	@Override
	public List<ResponseUnidadPrecioVO> getUnidadPrecio(List<Integer> idsUnidadesPrecio) {
		
	log.info("Empieza método consultarOfertaDeProducto");
	

	String endPoint = operacionesAuxiliaresEndPoint + methodgetUnidadPrecio + "/?listaId="
			+ idsUnidadesPrecio.toString().replace("[", "").replace("]", "");

	
	log.info("Llamada al servicio REST externo [{}] ", endPoint);
	

	return Arrays.asList(restTemplate.getForObject(endPoint, ResponseUnidadPrecioVO[].class));
	
	}

	@Override
	public List<Integer> getTypeProductAssociatedServices(List<Integer> idnServAtr) {
		
		log.info("Empieza método getTypeProductAssociatedServices");
		

		String endPoint = operacionesAuxiliaresEndPoint + methodgetTypeProductAssociatedServices + "?idnServAtr="
				+ idnServAtr.toString().replace("[", "").replace("]", "");

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		

		return Arrays.asList(restTemplate.getForObject(endPoint, Integer[].class));
		
	}

	@Override
	public Boolean isServiceSlot(List<Integer> idServAtr) {
		
		log.info("Empieza método isServiceSlot");
		

		String endPoint = operacionesAuxiliaresEndPoint + methodgetIsServiceSlot + "?idnServAtr="
				+ idServAtr.toString().replace("[", "").replace("]", "");

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		

		return restTemplate.getForObject(endPoint, Boolean.class);
	}
	
	public List<LocalDateTime> getDiaGas(LocalDateTime fecha) {

		List<LocalDateTime> diaGas = new ArrayList<>();
		List<String> salida = new ArrayList<>();
		String fechaux = fecha.toString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").ISO_LOCAL_DATE_TIME;

		try {

			salida = restTemplate
					.getForEntity(operacionesAuxiliaresEndPoint + methodGetGetDiaGas + fechaux,
							List.class)
					.getBody();

			diaGas.add(LocalDateTime.parse(salida.get(0), formatter));
			diaGas.add(LocalDateTime.parse(salida.get(1), formatter));

		} catch (HttpClientErrorException ex) { // Cuando se recibe algún error 4xx
			ex.printStackTrace();
		} catch (HttpServerErrorException ex) { // Cuando se recibe algún error 5xx
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return diaGas;

	}

	@Override
	public List<ResponseServOfertadoVO> getServiciosOfertados() {
		
		
		log.info("Empieza método getServiciosOfertados");
		

		String endPoint = operacionesAuxiliaresEndPoint + methodGetTservofertado;

		
		log.info("Llamada al servicio REST externo [{}] ", endPoint);
		

		return Arrays.asList(restTemplate.getForObject(endPoint, ResponseServOfertadoVO[].class));
	}

}
