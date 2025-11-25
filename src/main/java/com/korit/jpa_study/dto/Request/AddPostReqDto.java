package com.korit.jpa_study.dto.Request;

import com.korit.jpa_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AddPostReqDto {
    private String title;
    private String content;
    private Integer userId;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .createDt(LocalDateTime.now())
                .build();
    }
}
