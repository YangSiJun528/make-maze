package com.example.makemaze.service.social;


import com.example.makemaze.helper.constants.SocialLoginType;

public interface SocialOauth {
    /**
     * 각 Social Login 페이지로 Redirect 처리할 URL Build
     * 사용자로부터 로그인 요청을 받아 Social Login Server 인증용 code 요
     */
    String getOauthRedirectURL();

    /**
     * API Server로부터 받은 code를 활용하여 사용자 인증 정보 요청
     * @param code API Server 에서 받아온 code
     * @return API 서버로 부터 응답받은 Json 형태의 결과를 string으로 반
     */
    String requestAccessToken(String code);

    default SocialLoginType type() {
        if (this instanceof GoogleOauth) {
            return SocialLoginType.GOOGLE;
        } else if (this instanceof NaverOauth) {
            return SocialLoginType.NAVER;
        } else if (this instanceof KakaoOauth) {
            return SocialLoginType.KAKAO;
        } else {
            return null;
        }
    }

    /**
     * Client로부터 받은 refresh_token을 활용하여 access_token을 refresh 요청
     * @param refreshToken API Server 에서 받아온 code
     * @return 소셜 서버 로부터 받은 Json 형태의 결과를 string으로 반환
     */
    String refreshAccessToken(String refreshToken);
}
