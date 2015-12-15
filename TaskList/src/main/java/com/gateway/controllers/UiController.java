package com.gateway.controllers;

import com.gateway.secure.GoogleTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;


/**
 * Created by Jayavardhan on 12/5/15.
 */

@org.springframework.stereotype.Controller
public class UiController {

    @Autowired
    GoogleTokenServices googleTokenServices;

    @Autowired
    private OAuth2RestOperations oauth2RestTemplate;

    @RequestMapping(value="/login/token")
    public String welcome(HttpServletRequest request,@AuthenticationPrincipal Authentication authentication_check){

        if(authentication_check==null) {
            String accessToken = oauth2RestTemplate.getAccessToken().getValue();
            Authentication authentication = googleTokenServices.loadAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return "redirect:/taskList/home";
    }


    @RequestMapping(value = "/home")
    public String ask(@AuthenticationPrincipal Authentication authentication){

        if(authentication == null){
            return "login";
        }
        return "redirect:/taskList/home";
    }


    @RequestMapping(value="/")
    public String home(){
        return "redirect:/home";
    }

    @RequestMapping(value="/logout")
    public String logout(){

        return "logout";
    }
}
