package space.ebyte.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.ebyte.oauth2.DiscordOAuth2Guild;
import space.ebyte.oauth2.DiscordOAuth2GuildsService;
import space.ebyte.oauth2.DiscordOAuth2User;

import java.util.List;

@RestController
@RequestMapping("/guilds")
@RequiredArgsConstructor
public class GuildController {

    private final DiscordOAuth2GuildsService guildsService;

    @GetMapping
    public ResponseEntity<List<DiscordOAuth2Guild>> getGuilds(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                                                              @AuthenticationPrincipal DiscordOAuth2User user) {
        return guildsService.getGuilds(client);
    }
}
