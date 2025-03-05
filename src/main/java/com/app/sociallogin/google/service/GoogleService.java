package com.app.sociallogin.google.service;

import com.app.sociallogin.google.dto.GoogleAccount;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleService {

    @Value("${google.client.id}")
    private String GOOGLE_CLIENT_ID;

    @Value("${google.client.secret}")
    private String GOOGLE_CLIENT_SECRET;

    @Value("${google.redirect.url}")
    private String GOOGLE_REDIRECT_URL;

    private final static String GOOGLE_AUTH_URI = "https://accounts.google.com/o/oauth2/v2/auth";
    private final static String GOOGLE_TOKEN_URI = "https://oauth2.googleapis.com/token";
    private final static String GOOGLE_RESOURCE_URI = "https://www.googleapis.com/oauth2/v2/userinfo";
    private final ObjectMapper objectMapper;

    public String getGoogleLogin() {
        return GOOGLE_AUTH_URI + "?client_id=" + GOOGLE_CLIENT_ID
                + "&redirect_uri=" + GOOGLE_REDIRECT_URL
                + "&response_type=code&scope=email profile";
    }

    public GoogleAccount getGoogleAccountInfo(String code) throws Exception {
        if (code == null) throw new Exception("Failed get authorization code");

        String accessToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type"   , "authorization_code");
            params.add("client_id"    , GOOGLE_CLIENT_ID);
            params.add("client_secret", GOOGLE_CLIENT_SECRET);
            params.add("code"         , code);
            params.add("redirect_uri" , GOOGLE_REDIRECT_URL);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(GOOGLE_TOKEN_URI, HttpMethod.POST, httpEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                accessToken = jsonNode.path("access_token").asText();
            }
        } catch (Exception e) {
            throw new Exception("API call failed");
        }

        return getUserInfoWithToken(accessToken);
    }

    private GoogleAccount getUserInfoWithToken(String accessToken) throws Exception {
        //HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        //HttpHeader 담기
        RestTemplate restTemplate = new RestTemplate();
        JsonNode userResourceNode = restTemplate.exchange(GOOGLE_RESOURCE_URI, HttpMethod.GET, httpEntity, JsonNode.class).getBody();

        if (userResourceNode == null) throw new Exception("API call failed");

        return GoogleAccount.builder()
                .id(userResourceNode.get("id").asLong())
                .name(userResourceNode.get("name").asText())
                .email(userResourceNode.get("email").asText())
                .build();
    }


}
