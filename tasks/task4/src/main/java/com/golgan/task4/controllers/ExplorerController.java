package com.golgan.task4.controllers;

import com.golgan.task4.services.ExplorerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.nio.file.*;


@Controller
public class ExplorerController {

    private final ExplorerService explorerService;

    public ExplorerController(ExplorerService explorerService) {
        this.explorerService = explorerService;
    }


    @GetMapping
    public String showDirectory(@RequestParam(required = false) String path, Model model) {
        explorerService.getDirectoryData(path, model);

        return "directory";
    }
}
