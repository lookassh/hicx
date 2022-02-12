package com.hicx.stat;

import com.hicx.batch.StringBatchData;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class NumOfWordsStatisticTest {

    @Test
    void multiLineCount() {
        var numOfWordsStatistic = new NumOfWordsStatistic();
        numOfWordsStatistic.visit(new StringBatchData("Reading short stories in English is a great way to improve your language level. In this section, read our short stories that were specially written for English language learners. There are two sections, one for lower level learners (A2/B1) and one for higher levels (B2/C1)"));
        numOfWordsStatistic.visit(new StringBatchData("You will improve your reading fluency and comprehension and develop your vocabulary. Each story has interactive exercises to help you understand and use the language."));
        assertThat(numOfWordsStatistic.getCount(), is(70L));
    }

    @Test
    void shortStringCount() {
        var numOfWordsStatistic = new NumOfWordsStatistic();
        numOfWordsStatistic.visit(new StringBatchData("Reading short stories"));
        assertThat(numOfWordsStatistic.getCount(), is(3L));
    }

    @Test
    void emptyString() {
        var numOfWordsStatistic = new NumOfWordsStatistic();
        numOfWordsStatistic.visit(new StringBatchData(" "));
        assertThat(numOfWordsStatistic.getCount(), is(0L));
    }
}