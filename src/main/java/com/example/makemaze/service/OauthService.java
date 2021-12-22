package com.example.makemaze.service;

import com.example.makemaze.dto.OAuthCallbackResponseDto;
import com.example.makemaze.dto.RefreshingRequestDto;
import com.example.makemaze.helper.constants.SocialLoginType;
import com.example.makemaze.service.social.SocialOauth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.requestAccessToken(code);
    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

    public String refreshAccessToken(SocialLoginType socialLoginType, String refreshingRequestDto) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.refreshAccessToken(refreshingRequestDto);
    }

    public OAuthCallbackResponseDto callbackJsonToDto(String callbackJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthCallbackResponseDto oAuthCallbackResponseDto = objectMapper.readValue(callbackJson, OAuthCallbackResponseDto.class);
        return oAuthCallbackResponseDto;
    }
}
