package com.hicx.stat;

import com.hicx.batch.BatchVisitor;
import com.hicx.batch.StringBatchData;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Slf4j
public class NumOfWordsStatistic implements Statistic {

    @Getter
    private long count = 0;

    @Override
    public void visit(@NonNull StringBatchData batchData) {
        count += Arrays.stream(batchData.getLine().trim().split("\\s+"))
                .filter(StringUtils::hasText)
                .count();
    }

    @Override
    public void print() {
        log.info("Number of words: {}", count);
    }

}
