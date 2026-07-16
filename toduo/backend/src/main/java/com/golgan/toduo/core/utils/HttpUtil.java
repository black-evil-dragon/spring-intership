package com.golgan.toduo.core.utils;

import com.golgan.toduo.modules.auth.dto.TokenPairDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import static com.golgan.toduo.core.config.TokenDuration.REFRESH_TOKEN_EXPIRATION;

public class HttpUtil {
    public static String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null || xfHeader.isEmpty() || "unknown".equalsIgnoreCase(xfHeader)) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0].trim();
    }


    public static String getValueFromCookie(Cookie[] cookies, String key) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    // * TOKENS UTILS

    public static void setTokens(HttpServletResponse httpResponse, TokenPairDto tokens) {
        setCookieRefreshToken(httpResponse, tokens.refreshToken());
        setHeaderAccessToken(httpResponse, tokens.accessToken());
    }

    // * REFRESH TOKEN
    public static void setCookieRefreshToken(HttpServletResponse httpResponse, String refreshToken) {
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
            .httpOnly(true)
            //.secure(true)
            .path("/")
            .maxAge(REFRESH_TOKEN_EXPIRATION)
            .sameSite("Strict")
            .build();
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    public static String getRefreshTokenFromCookie(Cookie[] cookies) {
        return getValueFromCookie(cookies, "refreshToken");
    }


    public static void deleteCookieRefreshToken(HttpServletResponse httpResponse) {
        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
            .httpOnly(true)
            // .secure(true)
            .path("/")
            .maxAge(0)
            .sameSite("Strict")
            .build();
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
    }


    // * ACCESS TOKEN
    public static String getHeaderAccessToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public static void setHeaderAccessToken(HttpServletResponse httpResponse, String accessToken) {
        httpResponse.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
    }

}
