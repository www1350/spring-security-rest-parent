package com.absurd.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.absurd.web")
public class WebConfig extends WebMvcConfigurerAdapter {

    // beans

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    //

}