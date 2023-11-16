package com.example.coches.configuracion;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

	 @SuppressWarnings("deprecation")
	@Bean
	    public CookieLocaleResolver localeResolver() {
	        CookieLocaleResolver resolver = new CookieLocaleResolver();
	        resolver.setDefaultLocale(Locale.US); // Establece el idioma predeterminado si lo deseas
	        resolver.setCookieName("myLocaleCookie"); // Nombre de la cookie
	        resolver.setCookieMaxAge(4800); // Duración máxima de la cookie
	        return resolver;
	    }
	 @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	        interceptor.setParamName("lang"); // Define el nombre del parámetro para cambiar el idioma
	        return interceptor;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(localeChangeInterceptor());
	    }
	    @Bean
	    public ResourceBundleMessageSource messageSource() {
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("messages.properties"); 
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }

}
