package space.ebyte.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@UtilityClass
public class OAuth2TokenUtil {

    public String getHeaderValue(OAuth2AccessToken token) {
        return token.getTokenType().getValue() + " " + token.getTokenValue();
    }
}