package com.golgan.toduo.modules.desks.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class ColumnNotFoundException extends HttpApplicationException {
    public ColumnNotFoundException() {
        super(DeskErrorType.COLUMN_NOT_FOUND);
    }
}
