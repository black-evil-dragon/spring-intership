package com.golgan.toduo.modules.desks.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class ColumnLeftAloneAndSad extends HttpApplicationException {
    public ColumnLeftAloneAndSad() {
        super(DeskErrorType.COLUMN_ALONE);
    }
}
