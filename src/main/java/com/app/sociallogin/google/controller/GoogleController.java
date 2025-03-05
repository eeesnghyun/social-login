package com.app.sociallogin.google.controller;

import com.app.sociallogin.common.MsgEntity;
import com.app.sociallogin.google.dto.GoogleAccount;
import com.app.sociallogin.google.service.GoogleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("google")
public class GoogleController {

    private final GoogleService googleService;

    @GetMapping("/callback")
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        GoogleAccount account = googleService.getGoogleAccountInfo(String.valueOf(request.getParameter("code")));

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", account));
    }

}
