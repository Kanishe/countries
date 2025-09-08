package com.aka.countries.config;

import com.aka.countries.exception.CountryDefaultErrorAttributes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AppConfig {

    @Value("${spring.application.version}")
    private String apiVersion;

    @Bean
    ObjectMapper mapper() {
        return new ObjectMapper()
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new CountryDefaultErrorAttributes(apiVersion);
    }

}
