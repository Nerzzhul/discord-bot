package space.ebyte.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import space.ebyte.api.DiscordApi;
import space.ebyte.utils.OAuth2TokenUtil;

@Service
@RequiredArgsConstructor
public class GuildMemberService {

    private final DiscordApi discordApi;

    public ResponseEntity<String> getMember(OAuth2AuthorizedClient client, String guildId) {
        return discordApi.getGuildMember(OAuth2TokenUtil.getHeaderValue(client.getAccessToken()), guildId);
    }
}