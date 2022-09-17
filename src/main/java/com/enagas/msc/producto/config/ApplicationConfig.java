package com.enagas.msc.producto.config;

import static java.util.Locale.ENGLISH;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.enagas.arch.core.annotations.EnaConfiguration;

/**
 * Application config class to configure beans initialization.
 */
@EnaConfiguration
public class ApplicationConfig {

	/**
	 * Locale initialization.
	 *
	 * @return the locale resolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(ENGLISH);
		return sessionLocaleResolver;
	}
}