package space.ebyte.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestOperations;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestOperations restOperations(RestTemplateBuilder builder,
                                         @Value("${discord.user-agent:Discord Bot (http://localhost:8080/)}") String userAgent) {
        builder.additionalRequestCustomizers(request ->
                request.getHeaders()
                        .add(HttpHeaders.USER_AGENT, userAgent));
        return builder.build();
    }
}
