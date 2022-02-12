package com.hicx.stat;

import com.hicx.batch.BatchVisitor;
import com.hicx.batch.StringBatchData;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class NumOfWordsStatistic implements BatchVisitor {

    @Getter
    private long count = 0;

    @Override
    public void visit(@NonNull StringBatchData batchData) {
        count += Arrays.stream(batchData.getLine().trim().split("\\s+"))
                .filter(StringUtils::hasText)
                .count();
    }

}
