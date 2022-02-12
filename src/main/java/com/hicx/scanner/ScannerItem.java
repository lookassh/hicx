package com.hicx.scanner;

import org.springframework.core.io.Resource;

public interface ScannerItem {
    Resource getResource();
    void markAsProcessed();
    boolean isProcessed();
}
