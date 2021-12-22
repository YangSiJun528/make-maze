package com.example.makemaze.service;

import com.example.makemaze.dto.OAuthCallbackResponseDto;
import com.example.makemaze.dto.UserDto;
import com.example.makemaze.helper.constants.SocialLoginType;
import com.example.makemaze.repository.UserRepository;
import com.example.makemaze.service.social.SocialOauth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSevice {
    private final UserRepository userRepository;

    public void addUser() {
    }
/*
    public Map findUser(String UserInfo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
//        OAuthCallbackResponseDto oAuthCallbackResponseDto = objectMapper.readValue(UserInfo, OAuthCallbackResponseDto.class);
        Map<String, Object> jsonMap
                = objectMapper.readValue(UserInfo, new TypeReference<Map<String, Object>>() {});
        log.info("OAuthCallbackResponseDto :: {}",jsonMap);
        return jsonMap;
    }

 */
}
