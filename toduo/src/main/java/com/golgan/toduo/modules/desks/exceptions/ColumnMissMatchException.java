package com.golgan.toduo.modules.desks.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class ColumnMissMatchException extends HttpApplicationException {
    public ColumnMissMatchException() {
        super(DeskErrorType.COLUMN_MISMATCH);
    }
}
