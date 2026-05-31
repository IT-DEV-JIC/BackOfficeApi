package com.jicjo.apis.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityHeadersFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Strict Transport Security
        response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");

        // Content Security Policy
        response.setHeader("Content-Security-Policy",
                "default-src 'self'; " +
                        "script-src 'self' https://www.google.com/recaptcha/ https://www.gstatic.com/recaptcha/; " +
                        "style-src 'self'; " + // removed unsafe-inline for better security
                        "img-src 'self' data:; " +
                        "frame-src https://www.google.com/recaptcha/; " +
                        "object-src 'none';"
        );

        // Referrer Policy
        response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");

        // Permissions Policy
        response.setHeader("Permissions-Policy", "geolocation=(), camera=(), microphone=()");

        // Prevent MIME sniffing
        response.setHeader("X-Content-Type-Options", "nosniff");

        // Clickjacking protection
        response.setHeader("X-Frame-Options", "DENY");

        chain.doFilter(request, response);
    }
}
