package space.ebyte.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestOperations;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Value("${discord.api.user-agent:Discord Bot (http://localhost:8080/)}")
    private String userAgent;

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            request.getHeaders().add(HttpHeaders.USER_AGENT, userAgent);
            return execution.execute(request, body);
        };
        return new RestTemplateBuilder(restTemplate -> restTemplate.setInterceptors(List.of(interceptor)));
    }

    @Bean
    public RestOperations restOperations() {
        return restTemplateBuilder().build();
    }
}
