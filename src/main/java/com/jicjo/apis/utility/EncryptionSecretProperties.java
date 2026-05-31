package com.jicjo.apis.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Configuration
@ConfigurationProperties(prefix = "encryption.secret.key")
public class EncryptionSecretProperties {
    private String aes1;
    private String aes2;
    private String credentialsusername;
    private String credentialspassword;
    private String mailerusername;
    private String mailerpassword;
}
