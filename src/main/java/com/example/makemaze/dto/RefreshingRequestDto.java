package com.example.makemaze.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class RefreshingRequestDto {
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("google_id")
    private Long googleId;
}
