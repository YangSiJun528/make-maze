package com.example.makemaze.service.social;

import org.springframework.stereotype.Component;

@Component
public class NaverOauth implements SocialOauth {
    @Override
    public String getOauthRedirectURL() {
        return "";
    }

    @Override
    public String requestAccessToken(String code) {
        return null;
    }

    @Override
    public String refreshAccessToken(String refreshToken) {
        return null;
    }
}
