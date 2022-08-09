package space.ebyte.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.ebyte.api.DiscordApi;
import space.ebyte.models.DiscordGuild;
import space.ebyte.service.GuildMemberService;
import space.ebyte.service.GuildsService;
import space.ebyte.models.DiscordOAuth2User;
import space.ebyte.utils.OAuth2TokenUtil;

import java.util.List;

@RestController
@RequestMapping("/guilds")
@RequiredArgsConstructor
@Slf4j
public class GuildController {

    private final GuildsService guildsService;
    private final GuildMemberService guildMemberService;

    @GetMapping
    public ResponseEntity<List<DiscordGuild>> getUserGuilds(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                                                            @AuthenticationPrincipal DiscordOAuth2User user) {
        log.info("Guilds request by user: {}", user);
        return guildsService.getUserGuilds(client);
    }

    @GetMapping("/{guildId}/member")
    public ResponseEntity<String> getGuildMember(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client,
                                                 @PathVariable String guildId) {
        return guildMemberService.getMember(client, guildId);
    }
}
