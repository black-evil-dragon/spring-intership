package com.golgan.toduo.core.security;

import com.golgan.toduo.modules.auth.services.JwtService;
import com.golgan.toduo.modules.auth.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final JwtUtil jwtUtil; // Нужен для извлечения данных из токена

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Если заголовка нет или он не начинается с Bearer — пропускаем запрос дальше
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = authHeader.substring(7);

        // Используем ваш метод валидации stateless в памяти
        if (jwtService.validateAccessToken(accessToken)) {
            // Извлекаем данные из токена с помощью вашего JwtUtil
            Claims claims = jwtUtil.extractAllClaims(accessToken);
            String email = claims.getSubject();

            // Извлекаем роли, которые мы положили на шаге 2
            List<String> roles = claims.get("roles", List.class);
            List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();


            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                email, null, authorities
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Сохраняем пользователя в контекст Spring Security
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}