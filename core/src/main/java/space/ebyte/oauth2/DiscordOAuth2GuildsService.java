package space.ebyte.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import space.ebyte.utils.OAuth2TokenUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscordOAuth2GuildsService {

    private final RestOperations restOperations;
    private final OAuth2AuthorizedClientService clientService;

    public ResponseEntity<List<DiscordOAuth2Guild>> getGuilds(OAuth2AuthenticationToken authentication) {

        var client = clientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName());

        var userInfoUrl = client.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUri();

        return restOperations.exchange(
                userInfoUrl + "/guilds",
                HttpMethod.GET,
                new HttpEntity<>(getHeaders(client)),
                new ParameterizedTypeReference<>() {
                });
    }

    private HttpHeaders getHeaders(OAuth2AuthorizedClient client) {
        var headers = new HttpHeaders();
        headers.add(
                HttpHeaders.AUTHORIZATION,
                OAuth2TokenUtil.getHeaderValue(client.getAccessToken()));
        return headers;
    }
}