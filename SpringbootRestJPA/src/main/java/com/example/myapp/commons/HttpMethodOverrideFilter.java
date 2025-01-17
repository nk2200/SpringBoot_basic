package com.example.myapp.commons;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class HttpMethodOverrideFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String methodOverride = httpRequest.getHeader("X-HTTP-Method-Override"); //요청 중에 X-HTTP-Method-Override가져와서
		
		if(methodOverride != null && !methodOverride.isEmpty()) {
			//wrapper를 생성해서 http 메서드를 오버라이드한다.
			HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {
				
				@Override
				public String getMethod() {
					return methodOverride;
				}
			};
			chain.doFilter(wrapper, response); //래퍼를 다음 필터로 전달
		}else {
			chain.doFilter(httpRequest, response);//기존 요청을 그대로 전달
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		//초기화 코드
	}

	@Override
	public void destroy() {
		//필터 종료시 정리해야할 리소스가 있다면
	}
	
	

}
