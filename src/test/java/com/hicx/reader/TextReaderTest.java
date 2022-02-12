package com.hicx.reader;

import com.hicx.batch.BatchVisitor;
import com.hicx.batch.StringBatchData;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {
    @Test
    void name() {
        List<String> lines = new ArrayList<>();
        TextReader textReader = new TextReader(new ClassPathResource("tony-stark.txt"));
        textReader.readInBatches(batchData -> batchData.accept(batch -> {
            lines.add(batch.getLine());
        }));
        assertThat(lines.size(), is(2));
        assertThat(lines.get(0), is("Anthony Edward \"Tony\" Stark was a billionaire industrialist, a founding member of the Avengers, and the former CEO of Stark Industries."));
        assertThat(lines.get(1), is("A brash but brilliant inventor, Stark was self-described as a genius, billionaire, playboy, and philanthropist."));
    }
}