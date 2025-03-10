package com.app.sociallogin;

import com.app.sociallogin.apple.service.AppleService;
import com.app.sociallogin.google.service.GoogleService;
import com.app.sociallogin.kakao.service.KakaoService;
import com.app.sociallogin.naver.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AppleService appleService;
    private final KakaoService kakaoService;
    private final NaverService naverService;
    private final GoogleService googleService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("appleUrl", appleService.getAppleLogin());
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        model.addAttribute("naverUrl", naverService.getNaverLogin());
        model.addAttribute("googleUrl", googleService.getGoogleLogin());

        return "index";
    }

}
