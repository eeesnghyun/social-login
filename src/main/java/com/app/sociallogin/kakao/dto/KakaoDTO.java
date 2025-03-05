package com.app.sociallogin.kakao.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoDTO {

    private Long id;
    private String email;
    private String nickname;

}
