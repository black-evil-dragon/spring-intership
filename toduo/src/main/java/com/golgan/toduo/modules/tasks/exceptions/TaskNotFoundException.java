package com.golgan.toduo.modules.tasks.exceptions;

import com.golgan.toduo.core.exceptions.HttpApplicationException;

public class TaskNotFoundException extends HttpApplicationException {
    public TaskNotFoundException() {
        super(TaskErrorType.TASK_NOT_FOUND);
    }
}
