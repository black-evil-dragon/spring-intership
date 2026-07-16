package com.golgan.toduo.modules.auth.dto;

import com.golgan.toduo.modules.users.dto.UserDetailDto;

public record LoginResponseDto(
    UserDetailDto user,
    String token
) {

}
