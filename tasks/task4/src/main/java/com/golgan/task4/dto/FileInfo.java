package com.golgan.task4.dto;

import java.time.Instant;

public record FileInfo(
    String name,
    String absolutePath,
    boolean isDirectory,
    long size,
    Instant lastModified
) {

    @Override
    public String absolutePath() {
        if (absolutePath == null) return null;
        return absolutePath.replace("\\", "/");
    }
}
