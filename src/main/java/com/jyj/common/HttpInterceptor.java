package com.jyj.common;

import com.jyj.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map parameterMap = request.getParameterMap();
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
        log.info("request start. url:{},params:{}", url, JsonMapper.obj2String(parameterMap));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String url = request.getRequestURI().toString();
        Map parameterMap = request.getParameterMap();
        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request finished . url:{},params:{},cost:{}", url, JsonMapper.obj2String(parameterMap), end - start);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        Map parameterMap = request.getParameterMap();
        long start = (long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request completed . url:{},params:{},cost:{}", url, JsonMapper.obj2String(parameterMap), end - start);
    }
}
