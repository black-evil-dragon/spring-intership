package com.golgan.toduo.modules.auth.dto;

public record LoginByEmailRequestDto(
    String email,
    String password
) {}
