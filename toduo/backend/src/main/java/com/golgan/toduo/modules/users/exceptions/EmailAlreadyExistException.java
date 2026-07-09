package com.golgan.toduo.modules.users.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class EmailAlreadyExistException extends HttpApplicationException {
    public EmailAlreadyExistException() {
        super(UserErrorType.EMAIL_ALREADY_EXISTS);
    }
}
