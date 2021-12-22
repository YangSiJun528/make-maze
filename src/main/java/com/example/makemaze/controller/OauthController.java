package com.example.makemaze.controller;

import antlr.Token;
import com.example.makemaze.dto.OAuthCallbackResponseDto;
import com.example.makemaze.dto.RefreshingRequestDto;
import com.example.makemaze.dto.UserDto;
import com.example.makemaze.helper.constants.SocialLoginType;
import com.example.makemaze.jwt.JwtService;
import com.example.makemaze.service.OauthService;
import com.example.makemaze.service.UserSevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.service.NullServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/oauth")
@Slf4j
public class OauthController {
    private final OauthService oauthService;
    private final JwtService jwtService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     */
    @GetMapping(value = "/{socialLoginType}")
    public void socialLoginType(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }

    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param socialLoginType (GOOGLE, NAVER, KAKAO)
     * @param code API Server 로부터 넘어노는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @GetMapping(value = "/{socialLoginType}/callback")
    public ResponseEntity callback(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code) throws JsonProcessingException {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        String result = oauthService.requestAccessToken(socialLoginType, code);
        OAuthCallbackResponseDto obj = oauthService.callbackJsonToDto(result);
        obj.setIdToken("\""+obj.getIdToken()+"\"");
        Token token = jwtService.decode(obj.getIdToken());
        return new ResponseEntity(token, HttpStatus.OK);
    }

    /**
     * Client의 access_token refreshing 요청에 의한 new access_token 반환
     * @RequestBody
     *  {
     *      refresh_token : String
     *  }
     * @return Json 형태의 String 문자열 (access_token, refresh_token, ...etc)
     */
    @PostMapping(value = "/{socialLoginType}/refreshing")
    public ResponseEntity refresh(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestBody RefreshingRequestDto refreshingRequestDto) {
        log.info(">> Client로부터 받은 refresh_token :: {}", refreshingRequestDto.getRefreshToken());
        log.info(">> Client로부터 받은 google_id :: {}", refreshingRequestDto.getGoogleId());
        String result = oauthService.refreshAccessToken(socialLoginType, refreshingRequestDto.getRefreshToken());
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
