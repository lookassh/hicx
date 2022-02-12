package com.hicx.stat;

import com.hicx.batch.StringBatchData;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompositeStatistic implements Statistic {

    private final List<Statistic> statistics;

    @Override
    public void visit(StringBatchData batchData) {
        statistics.forEach(batchData::accept);
    }

    @Override
    public void print() {
        statistics.forEach(Statistic::print);
    }

}
