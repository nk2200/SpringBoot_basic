package com.example.myapp.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.myapp.commons.HttpMethodOverrideFilter;

import jakarta.servlet.Filter;
@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Bean
	FilterRegistrationBean<HttpMethodOverrideFilter> httpMethodOverrideFilter(){
		FilterRegistrationBean<HttpMethodOverrideFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new HttpMethodOverrideFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
	
	@Bean
	Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
}
