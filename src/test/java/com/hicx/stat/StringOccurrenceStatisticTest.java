package com.hicx.stat;

import com.hicx.batch.StringBatchData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class StringOccurrenceStatisticTest {

    @Test
    void wordCount() {
        var stringOccurrenceStatistic = new StringOccurrenceStatistic("and");
        stringOccurrenceStatistic.visit(new StringBatchData("Reading short stories in English is a great way to improve your language level. In this section, read our short stories that were specially written for English language learners. There are two sections, one for lower level learners (A2/B1) and one for higher levels (B2/C1)"));
        stringOccurrenceStatistic.visit(new StringBatchData("You will improve your reading fluency and comprehension and develop your vocabulary. Each story has interactive exercises to help you understand and use the language."));
        assertThat(stringOccurrenceStatistic.getCount(), is(5L));
    }

    @Test
    void emptyChar() {
        var stringOccurrenceStatistic = new StringOccurrenceStatistic("");
        stringOccurrenceStatistic.visit(new StringBatchData("Reading short stories"));
        assertThat(stringOccurrenceStatistic.getCount(), is(0L));
    }

    @Test
    void noData() {
        var stringOccurrenceStatistic = new StringOccurrenceStatistic("word");
        stringOccurrenceStatistic.visit(new StringBatchData(""));
        assertThat(stringOccurrenceStatistic.getCount(), is(0L));
    }
}