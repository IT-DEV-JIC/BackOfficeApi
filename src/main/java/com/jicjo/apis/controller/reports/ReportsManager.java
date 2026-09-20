package com.jicjo.apis.controller.reports;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reports")
public class ReportsManager implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Value("${encryption.secret.key.credentialsusername}")
    String username;

    @Value("${encryption.secret.key.credentialspassword}")
    String password;

    @PostMapping("/execute/{reportName:.+}")
    public ResponseEntity<byte[]> executeReport(
            @PathVariable String reportName,
            @RequestParam(defaultValue = "PDF") String printType,
            @RequestBody(required = false) Map<String, Object> parameters) {

        try {
            if (reportName.toLowerCase().endsWith(".rdl")) {
                reportName = reportName.substring(0, reportName.length() - 4);
            }

            String ssrsFormat;
            String contentType;
            String extension;
            String dispositionType;

            switch (printType.toUpperCase()) {
                case "EXCEL":
                    ssrsFormat = "EXCELOPENXML";
                    contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                    extension = "xlsx";
                    dispositionType = "attachment";
                    break;

                case "PDF":
                default:
                    ssrsFormat = "PDF";
                    contentType = "application/pdf";
                    extension = "pdf";
                    dispositionType = "inline";
                    break;
            }

            StringBuilder ssrsUrl = new StringBuilder("http://jic-portal:8080/ReportServer?/");
            ssrsUrl.append(URLEncoder.encode(reportName, StandardCharsets.UTF_8));
            ssrsUrl.append("&rs:Format=").append(ssrsFormat);

            if (parameters != null) {
                parameters.forEach((key, value) -> {
                    if (value != null
                            && !"username".equalsIgnoreCase(key)
                            && !"password".equalsIgnoreCase(key)
                            && !"printType".equalsIgnoreCase(key)) {
                        ssrsUrl.append("&")
                                .append(URLEncoder.encode(key, StandardCharsets.UTF_8))
                                .append("=")
                                .append(URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8));
                    }
                });
            }

            String user = this.username;
            String password = this.password;
            String domain = "JIC";

            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    AuthScope.ANY,
                    new NTCredentials(user, password, "", domain)
            );

            try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setDefaultCredentialsProvider(credsProvider)
                    .build()) {

                HttpGet httpGet = new HttpGet(ssrsUrl.toString());

                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    int statusCode = response.getStatusLine().getStatusCode();

                    System.out.println("SSRS URL: " + ssrsUrl);
                    System.out.println("SSRS Status: " + statusCode);

                    if (statusCode == 200) {
                        byte[] content = EntityUtils.toByteArray(response.getEntity());

                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.parseMediaType(contentType));
                        headers.setContentLength(content.length);
                        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                                dispositionType + "; filename=\"" + reportName + "." + extension + "\"");

                        return new ResponseEntity<>(content, headers, HttpStatus.OK);
                    } else {
                        String errorBody = response.getEntity() != null
                                ? EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8)
                                : "";

                        System.out.println("SSRS Error Body: " + errorBody);

                        return ResponseEntity.status(statusCode)
                                .contentType(MediaType.TEXT_PLAIN)
                                .body(("SSRS error: " + errorBody).getBytes(StandardCharsets.UTF_8));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(("Failed to execute report: " + e.getMessage()).getBytes(StandardCharsets.UTF_8));
        }
    }
}
