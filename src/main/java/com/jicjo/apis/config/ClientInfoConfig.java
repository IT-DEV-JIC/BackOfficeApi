package com.jicjo.apis.config;

import java.io.Serial;
import java.io.Serializable;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
public class ClientInfoConfig implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (attributes != null) ? attributes.getRequest() : null;
    }

    public String getClientIp() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) return "Unknown";

        String[] headers = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };

        for (String header : headers) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return normalizeIp(ip.split(",")[0].trim());
            }
        }

        return normalizeIp(request.getRemoteAddr());
    }

    public String getClientHostName() {
        String ip = getClientIp();
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            return "Unknown Host";
        }
    }

    /**
     * Convert IPv6 loopback to IPv4 loopback for readability.
     */
    private String normalizeIp(String ip) {
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }
}