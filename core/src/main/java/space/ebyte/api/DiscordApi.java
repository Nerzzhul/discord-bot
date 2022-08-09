package space.ebyte.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import space.ebyte.models.DiscordGuild;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DiscordApi {

    @Value("${discord.api.url}")
    private String url;

    private final RestOperations restOperations;

    public ResponseEntity<List<DiscordGuild>> getUserGuilds(String token) {
        return restOperations.exchange(
                url + "/users/@me/guilds",
                HttpMethod.GET,
                new HttpEntity<>(getHeaders(token)),
                new ParameterizedTypeReference<>() {
                });
    }

    public ResponseEntity<String> getGuildMember(String token, String guildId) {
        return restOperations.exchange(
                url + String.format("/users/@me/guilds/%s/member", guildId),
                HttpMethod.GET,
                new HttpEntity<>(getHeaders(token)),
                String.class);
    }

    private HttpHeaders getHeaders(String token) {
        var headers = new HttpHeaders();
        headers.add(
                HttpHeaders.AUTHORIZATION,
                token);
        return headers;
    }
}