package com.deals.isodeals.configuration;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class SystemConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry){
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
    @Bean
    public FilterRegistrationBean<?> corsFilter(){
        UrlBasedCorsConfigurationSource urlSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "OPTIONS", "DELETE", "PUT"));
        corsConfig.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        urlSource.registerCorsConfiguration("/**", corsConfig);
        FilterRegistrationBean<?> beanFilter = new FilterRegistrationBean<>(new CorsFilter());
        beanFilter.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return beanFilter;
    }
}
