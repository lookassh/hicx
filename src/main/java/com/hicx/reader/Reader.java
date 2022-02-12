package com.hicx.reader;

import com.hicx.batch.BatchData;

import java.util.function.Consumer;

public interface Reader {

    void readInBatches(Consumer<BatchData> consumer);

}
