package com.golgan.toduo.modules.desks.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class DeskNotFoundException extends HttpApplicationException {
    public DeskNotFoundException() {
        super(DeskErrorType.DESK_NOT_FOUND);
    }
}
