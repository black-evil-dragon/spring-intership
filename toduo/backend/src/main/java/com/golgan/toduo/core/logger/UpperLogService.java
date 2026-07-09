package com.golgan.toduo.core.logger;

import com.golgan.toduo.core.services.LoggerService;
import org.springframework.stereotype.Component;

@Component
public class UpperLogService implements LoggerService {
    @Override
    public void log(String message) {
        System.out.println(message.toUpperCase());
    }
}
