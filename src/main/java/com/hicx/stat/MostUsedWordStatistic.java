package com.hicx.stat;

import com.hicx.batch.BatchVisitor;
import com.hicx.batch.StringBatchData;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MostUsedWordStatistic implements Statistic {

    private final Map<String, Long> wordsMap = new HashMap<>();

    @Override
    public void visit(@NonNull StringBatchData batchData) {
        String[] words = batchData.getLine().trim().split("\\s+");
        Arrays.stream(words).forEach(word ->
                wordsMap.compute(word, (key, value)
                        -> value==null?1:++value)
        );
    }

    public Optional<String> getMostUsedWord(){
        return wordsMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    @Override
    public void print() {
        log.info("Most used word: {}", getMostUsedWord().orElse(""));
    }

}
