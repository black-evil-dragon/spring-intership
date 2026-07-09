package com.golgan.toduo.modules.users.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class UserNotFoundException extends HttpApplicationException {
    public UserNotFoundException() {
        super(UserErrorType.USER_NOT_FOUND);
    }
}
