package com.hicx.stat;

import com.hicx.batch.StringBatchData;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.util.StringUtils.countOccurrencesOf;

@Slf4j
public class StringOccurrenceStatistic implements Statistic {
    @NonNull
    private final String sub;
    @Getter
    private long count = 0;

    public StringOccurrenceStatistic(String sub) {
        this.sub = sub;
    }

    @Override
    public void visit(@NonNull StringBatchData batchData) {
        count += countOccurrencesOf(batchData.getLine(), sub);
    }

    @Override
    public void print() {
        log.info("Number of {}: {}", sub, count);
    }

}
