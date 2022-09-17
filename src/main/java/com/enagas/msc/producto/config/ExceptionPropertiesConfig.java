package com.enagas.msc.producto.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.enagas.msc.producto.exception.AppErrorCode;

import lombok.Data;


@PropertySource(factory = YamlAndPropertySourceFactory.class, value = {"classpath:exceptions/external_exceptions.yml"})
@Component
@Data
@ConfigurationProperties(prefix = "exception", ignoreUnknownFields = false)
public class ExceptionPropertiesConfig {
	
	private Map<String, Object> map;
	private List<ExcepcionProp> businessException;
	private List<ExcepcionProp> technicalException;
	private List<ExcepcionProp> securityException;

	/**
	 * Get the value of map
	 * 
	 * @param key String
	 * @return ExcepcionProp
	 */
	public ExcepcionProp get(String key) {
		return (ExcepcionProp) map.get(key);
	}

	@PostConstruct
	public void completeMapValues() {
		map = new HashMap<>();
		
		businessException.forEach((final ExcepcionProp excep) -> {
			processException(excep);			
		});
		technicalException.forEach((final ExcepcionProp excep) -> {
			processException(excep);			
		});
		securityException.forEach((final ExcepcionProp excep) -> {
			processException(excep);		
		});
	}
	
	/**
	 * Process all exceptions
	 * @param excep ExcepcionProp
	 */
	private void processException(ExcepcionProp excep) {
		AppErrorCode enume = AppErrorCode.findByName(excep.getCode());
		if (enume != null) {
			enume.changeReason(excep.desc);
		}
		map.put(excep.getCode(), excep);		
	}

	/**
	 * Clase estatica para definir los parametros de la excepcion
	 */
	@Data
	public static class ExcepcionProp {
		private String code;
		private String desc;
	}


}
