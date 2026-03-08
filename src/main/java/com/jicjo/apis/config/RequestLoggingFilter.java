package com.jicjo.apis.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    private final ClientInfoConfig clientInfoConfig;

    public RequestLoggingFilter(ClientInfoConfig clientInfoConfig) {
        this.clientInfoConfig = clientInfoConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String ip = clientInfoConfig.getClientIp();
        String host = clientInfoConfig.getClientHostName();
        String windowsUser = request.getRemoteUser();
        String RequestURI = request.getRequestURI();

        System.out.println("Incoming request → " + "IP: {" + ip + "} " + ",Host: {" + host + "}" +
                ",RemoteUser: {" + windowsUser + "}" + ",URI: {" + RequestURI + "} " + ",On " + new Date());

        logger.info("Incoming request → IP: {}, Host: {}, URI: {}", ip, host, RequestURI);

        filterChain.doFilter(request, response);
    }
}