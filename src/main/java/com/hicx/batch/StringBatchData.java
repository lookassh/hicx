package com.hicx.batch;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * Represents line in large file
 */
@Value
@AllArgsConstructor
public class StringBatchData implements BatchData {
    @NonNull
    String line;

    @Override
    public void accept(BatchVisitor visitor) {
        visitor.visit(this);
    }

}
