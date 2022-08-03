package space.ebyte.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestOperations;
import space.ebyte.oauth2.DiscordOAuth2User;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final RestOperations restOperations;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2UserService());
        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        var delegate = new DefaultOAuth2UserService();
        delegate.setRestOperations(restOperations);
        return request -> new DiscordOAuth2User(delegate.loadUser(request));
    }
}
