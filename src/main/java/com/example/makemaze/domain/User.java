package com.example.makemaze.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "google_id")
    private String googleId;
    @Column(name = "refresh_token")
    private String refreshToken;
    @OneToMany(mappedBy = "user")
    List<Map> maps = new ArrayList<Map>();
}