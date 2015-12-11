package com.gateway.secure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Jayavardhan on 12/4/15.
 */

@Configuration
@EnableOAuth2Client
 public class SpringConfiguration {

    @Resource
    @Qualifier("accessTokenRequest")
    private AccessTokenRequest accessTokenRequest;

    @Bean
    @Scope("session")
    public OAuth2ProtectedResourceDetails googleResource() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("google-oauth-client");
        details.setClientId("964675498365-fm41gtar6ie52klr0f19bmi9lvfr5qsf.apps.googleusercontent.com");
        details.setClientSecret("t4Xz71qdpfGzfJipLCc40e_l");
        details.setAccessTokenUri("https://accounts.google.com/o/oauth2/token");
        details.setUserAuthorizationUri("https://accounts.google.com/o/oauth2/auth");
        details.setTokenName("authorization_code");
        String commaSeparatedScopes = "https://www.googleapis.com/auth/userinfo.email,https://www.googleapis.com/auth/userinfo.profile";
        details.setScope(parseScopes(commaSeparatedScopes));
        details.setPreEstablishedRedirectUri("http://localhost:8080/login/token");
        details.setUseCurrentUri(false);
        details.setAuthenticationScheme(AuthenticationScheme.query);
        details.setClientAuthenticationScheme(AuthenticationScheme.form);
        return details;
    }

    private List<String> parseScopes(String commaSeparatedScopes) {
        List<String> scopes = newArrayList();
        Collections.addAll(scopes, commaSeparatedScopes.split(","));
        return scopes;
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2RestTemplate googleRestTemplate() {
        return new OAuth2RestTemplate(googleResource(), new DefaultOAuth2ClientContext(accessTokenRequest));
    }
}
