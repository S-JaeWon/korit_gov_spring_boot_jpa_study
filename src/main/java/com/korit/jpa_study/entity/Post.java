package com.korit.jpa_study.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // jpa
@Table(name = "post_tb") // 테이블 명
public class Post {
    @Id // PK 부여
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키 생성 후 반환
    private Integer postId;
    private String title;
    private String content;
    private Integer userId;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
