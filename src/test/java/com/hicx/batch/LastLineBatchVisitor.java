package com.hicx.batch;

import lombok.Getter;

public class LastLineBatchVisitor implements BatchVisitor{
    @Getter
    private String line;

    @Override
    public void visit(StringBatchData batchData) {
        line = batchData.getLine();
    }

}
