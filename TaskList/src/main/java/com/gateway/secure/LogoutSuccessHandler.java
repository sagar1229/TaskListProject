package com.gateway.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jayavardhan on 12/5/15.
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

    @Autowired
    GoogleTokenServices googleTokenServices;



    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = googleTokenServices.getTokenStore().readAccessToken(tokenValue);
            googleTokenServices.getTokenStore().removeAccessToken(accessToken);
            googleTokenServices.getTokenStore().removeRefreshToken(accessToken.getRefreshToken());
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        super.handle(request, response, authentication);
    }
}
