package com.hicx.stat;

import com.hicx.batch.BatchVisitor;
import com.hicx.batch.StringBatchData;
import lombok.Getter;
import lombok.NonNull;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class StringOccurrenceStatistic implements BatchVisitor {
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

}
