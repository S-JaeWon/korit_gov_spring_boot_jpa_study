package com.korit.jpa_study.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditUserReqDto {
    private Integer userId;
    private String username;
    private String password;
}
