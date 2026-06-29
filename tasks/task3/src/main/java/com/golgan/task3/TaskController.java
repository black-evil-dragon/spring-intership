package com.golgan.task1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {
    @GetMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return "<h1>Hello world!</>";
    }

    @GetMapping("/greet")
    @ResponseBody
    public String greet(@RequestParam String message) {
        return "Добро пожаловать, " + message;
    }


    /// # Калькулятор СВ1
    ///
    /// > Поддерживает операции `+` и `-`
    ///
    /// @param operation Операция. `plus`/`minus`
    /// @param num1 Первое число
    /// @param num2 Второе число
    /// @return Ответ или `9999999999.9` в случае не предвиденный гигакодером ситуацией
    @GetMapping("/calc/{operation}")
    @ResponseBody
    public double calculate(
        @PathVariable String operation,
        @RequestParam double num1,
        @RequestParam double num2
    ) {

        // Сервис логика
        double result;

        if ("plus".equalsIgnoreCase(operation)) {
            result = num1 + num2;
            return result;

        } else if ("minus".equalsIgnoreCase(operation)) {
            result = num1 - num2;
            return result;
        }

        return 9999999999.9;
    }
}
