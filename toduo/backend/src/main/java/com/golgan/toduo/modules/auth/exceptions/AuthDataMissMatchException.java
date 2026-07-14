package com.golgan.toduo.modules.auth.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class AuthDataMissMatchException extends HttpApplicationException {
    public AuthDataMissMatchException() {
        super(AuthErrorType.AUTH_MISS_MATCH);
    }
}
