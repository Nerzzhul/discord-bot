package space.ebyte.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class DiscordOAuth2User implements OAuth2User {

    private final OAuth2User oAuth2User;

    public String getId() {
        return oAuth2User.getAttribute("id");
    }

    public String getUsername() {
        return oAuth2User.getAttribute("username") + "#" + oAuth2User.getAttribute("discriminator");
    }

    public String getAvatarUrl() {
        return String.format("https://cdn.discordapp.com/avatars/%s/%s", getId(), oAuth2User.getAttribute("avatar"));
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }
}