package com.korit.jpa_study.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditPostReqDto {
    private Integer postId;
    private String title;
    private String content;

}
