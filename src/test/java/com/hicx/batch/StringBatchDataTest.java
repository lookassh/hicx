package com.hicx.batch;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class StringBatchDataTest {

    @Test
    void accept() {
        var visitor = new LastLineBatchVisitor();
        var batchData = new StringBatchData("Tony Stark");
        batchData.accept(visitor);
        assertThat(visitor.getLine(), is("Tony Stark"));
        assertThat(batchData.getLine(), is("Tony Stark"));
    }


}