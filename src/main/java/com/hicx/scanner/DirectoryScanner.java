package com.hicx.scanner;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
public class DirectoryScanner {
    public static final String PROCESSED = "processed";

    @NonNull
    private final Path dir;
    private final Path processedDir;

    public DirectoryScanner(@NonNull Path dir) throws IOException {
        this.dir = dir;
        Files.createDirectories(dir);
        this.processedDir = dir.resolve(PROCESSED);
        Files.createDirectories(processedDir);
    }

    public void scan(Consumer<ScannerItem> consumer) throws IOException {
        log.debug("Started scanning {}", dir.toAbsolutePath());
        try (Stream<Path> pathStream = Files.list(dir)) {
            pathStream.parallel()
                    .filter(Files::isRegularFile)
                    .map(FileSystemResource::new)
                    .map(FileSystemScannerItem::new)
                    .peek(consumer)
                    .filter(ScannerItem::isProcessed)
                    .map(FileSystemScannerItem::getResource)
                    .forEach(this::moveToProcessed);
        }
        log.debug("Scan finished");
    }

    @SneakyThrows
    void moveToProcessed(FileSystemResource fileSystemResource) {
        log.debug("Moving {} file to {} directory", fileSystemResource.getFilename(), processedDir);
        Files.move(fileSystemResource.getFile().toPath(), processedDir.resolve(fileSystemResource.getFilename()));
    }

}
