package com.project.hunter.utils;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.project.hunter.domain.dto.RestResponse;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ResponseFormatter implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        //catch type ServerHttpResponse -> ServletServerHttpResponse
        ServletServerHttpResponse servletServerHttpResponse = (ServletServerHttpResponse) response;
        //get httpServletResponse
        HttpServletResponse httpServletResponse = servletServerHttpResponse.getServletResponse();
        int statusCode = httpServletResponse.getStatus();
        if (statusCode >= 400) { //case error
            return body;
        }
        return new RestResponse<>(statusCode, "Call API successfully", body, null);

    }

}
