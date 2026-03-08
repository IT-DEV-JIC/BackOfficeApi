package com.jicjo.apis.utility;

import com.jicjo.apis.config.ClientInfoConfig;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;

@Service
public class AuditLogService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ClientInfoConfig clientInfoConfig;

    public AuditLogService(ClientInfoConfig clientInfoConfig) {
        this.clientInfoConfig = clientInfoConfig;
    }

    public void logAccess() {
        String ip = clientInfoConfig.getClientIp();
        String host = clientInfoConfig.getClientHostName();

        System.out.println("Request from IP: " + ip + ", Host: " + host);
        // You can now save these in DB or logs, etc.
    }
}
