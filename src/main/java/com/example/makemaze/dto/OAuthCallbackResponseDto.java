package com.example.makemaze.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuthCallbackResponseDto {

    @JsonProperty(value="access_token")
    private String accessToken;

    @JsonProperty(value="expires_in")
    private String expiresIn;

    @JsonProperty(value="refresh_token")
    private String refreshToken;

    @JsonProperty(value="scope")
    private String scope;

    @JsonProperty(value="token_type")
    private String tokenType;

    @JsonProperty(value="id_token")
    private String idToken;
}
