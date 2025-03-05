package com.app.sociallogin.google.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleAccount {

    private Long id;
    private String email;
    private String name;

}
