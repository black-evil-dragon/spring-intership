package com.golgan.toduo.core.logger;

import com.golgan.toduo.core.services.LoggerService;
import org.springframework.stereotype.Component;

// Практика 1.
@Component
public class LowerLogService implements LoggerService {

    @Override
    public void log(String message) {
        System.out.println(message.toLowerCase());
    }
}

