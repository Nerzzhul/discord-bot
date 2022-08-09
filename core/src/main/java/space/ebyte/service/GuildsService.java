package space.ebyte.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import space.ebyte.api.DiscordApi;
import space.ebyte.models.DiscordGuild;
import space.ebyte.utils.OAuth2TokenUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuildsService {

    private final DiscordApi discordApi;

    public ResponseEntity<List<DiscordGuild>> getUserGuilds(OAuth2AuthorizedClient client) {
        return discordApi.getUserGuilds(OAuth2TokenUtil.getHeaderValue(client.getAccessToken()));
    }
}