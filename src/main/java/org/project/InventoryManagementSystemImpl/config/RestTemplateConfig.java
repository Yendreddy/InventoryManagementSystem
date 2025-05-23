package org.project.InventoryManagementSystemImpl.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() throws Exception {
        SSLContextBuilder sslContextBuilder = SSLContextBuilder.create()
                .loadTrustMaterial(new TrustAllStrategy());

        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                        .setSslContext(sslContextBuilder.build())
                        .build())
                .setValidateAfterInactivity(Timeout.ofSeconds(1)) // Set validateAfterInactivity
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionKeepAlive(Timeout.ofSeconds(5))
                        .setResponseTimeout(Timeout.ofSeconds(5))
                        .setConnectTimeout(Timeout.ofSeconds(5))
                        .build())
                .setConnectionReuseStrategy((httpRequest, httpResponse, httpContext ) -> false) // Disable connection reuse
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(5000); // 5 seconds
        factory.setReadTimeout(5000); // 5 seconds
        return new RestTemplate(factory);
    }
}
