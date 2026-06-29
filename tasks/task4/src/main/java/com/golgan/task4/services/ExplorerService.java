package com.golgan.task4.services;


import com.golgan.task4.dto.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExplorerService {




    public void getDirectoryData(String path, Model model) {
        String targetPathStr = (path == null || path.isBlank()) ? System.getProperty("user.home") : path;
        Path currentPath = Paths.get(targetPathStr).toAbsolutePath().normalize();


        // Работаем с текущей директорией и проверяем наличие родительской
        model.addAttribute(
            "currentPath",
            currentPath.toString().replace("\\", "/")
        );

        if (currentPath.getParent() != null) {
            model.addAttribute(
                "parentPath",
                currentPath.getParent().toString().replace("\\", "/")
            );
        }


        // Собираем файлы
        try {
            List<FileInfo> files = getFiles(currentPath);
            model.addAttribute("files", files);

        } catch (IOException e) {
            model.addAttribute(
                "error",
                "Ошибка! " + e.getMessage()
            );
        }
    }


    private List<FileInfo> getFiles(Path currentPath) throws IOException {
        try (Stream<Path> stream = Files.list(currentPath)) {
            Stream<FileInfo> streamFiles = stream.map(p -> {
                try {
                    BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);

                    return new FileInfo(
                        p.getFileName().toString(),
                        p.toAbsolutePath().toString(),
                        attrs.isDirectory(),
                        attrs.isDirectory() ? 0 : attrs.size(),
                        attrs.lastModifiedTime().toInstant()
                    );
                } catch (IOException e) {
                    return null;
                }
            });

            return streamFiles.collect(Collectors.toList()
            );
        }
    }
}
