package com.example.makemaze.dto;

import com.example.makemaze.domain.Map;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private Long googleId;
    private String email;
    List<Map> maps = new ArrayList<Map>();
}