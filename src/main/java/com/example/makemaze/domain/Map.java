package com.example.makemaze.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "img")
    private String img;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
