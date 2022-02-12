package com.hicx.scanner;

import lombok.NonNull;
import org.springframework.core.io.FileSystemResource;

class FileSystemScannerItem implements ScannerItem{

    @NonNull
    private final FileSystemResource resource;
    private boolean processed;

    public FileSystemScannerItem(@NonNull FileSystemResource resource) {
        this.resource = resource;
        this.processed = false;
    }

    @Override
    public FileSystemResource getResource() {
        return resource;
    }

    @Override
    public void markAsProcessed() {
        this.processed = true;
    }

    @Override
    public boolean isProcessed() {
        return processed;
    }
}
