package com.enagas.msc.producto.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@PropertySource(value = { "classpath:parameters/enum.yml" }, ignoreResourceNotFound = true)
public class ParametersPropertiesConfig {
	
	/** Logger. */
	private static final Logger log = LoggerFactory.getLogger(ParametersPropertiesConfig.class);

	@Autowired
	private Environment env;

	private static String FOLDER_PROPERTIES = "parameters/";

	// Property map
	private Map<String, Object> map = new HashMap<>();

	public String get(String key) {
		return (String) map.get(key);
	}

	@PostConstruct
	public void completeMapValues() {

		for (Iterator<?> it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext();) {
			Object propertySource = it.next();
			if (propertySource instanceof ResourcePropertySource) {
				String name = ((MapPropertySource) propertySource).getName();
				if (name.contains(FOLDER_PROPERTIES)) {
					Map<?, ?> mapFiles = ((MapPropertySource) propertySource).getSource();
					for (Object file : mapFiles.keySet()) {
						map.put(file.toString(), mapFiles.get(file));
					}
				}
			}
		}
		
		log.info("Mapa de enumerados cargado {}: {}", FOLDER_PROPERTIES, map);
	}
}