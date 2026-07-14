package com.golgan.toduo.modules.auth.dto;

public record LoginByEmailRequest(
    String email,
    String password
) {}
