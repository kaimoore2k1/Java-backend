package com.senshop.backend.utils;

import java.util.Date;

import javax.servlet.http.Cookie;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JsonWebToken {
    private Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    
    public String createAccessToken(String username) {
        
        String accessToken = JWT.create()
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(algorithm);
        return accessToken;
    }

    public String createRefreshToken(String username, String tokenVersion) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String refreshToken = JWT.create()
                .withClaim("username", username)
                .withClaim("tokenVersion", tokenVersion)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(algorithm);
        return refreshToken;
    }

    public Cookie sendRefreshToken(String username, String tokenVersion) {
        Cookie cookie = new Cookie("senshop", createRefreshToken(username, createRefreshToken(username, tokenVersion)));
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/refresh_token");
        return cookie;
    }
}
