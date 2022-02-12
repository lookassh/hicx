package com.hicx.batch;

/**
 * Represents part of data
 */
public interface BatchData {
    void accept(BatchVisitor visitor);
}
