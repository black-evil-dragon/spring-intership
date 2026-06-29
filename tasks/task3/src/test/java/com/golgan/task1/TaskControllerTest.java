package com.golgan.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TaskControllerTest {


    private final TaskController controller = new TaskController();


    @Test
    void welcome() {
        String response = controller.greet("TEST");
        assertEquals("Добро пожаловать, TEST", response);
    }
}