package com.hicx.stat;

import com.hicx.batch.StringBatchData;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MostUsedWordStatisticTest {

    @Test
    void mostUsedWordMultiline() {
        var mostUsedWordStatistic = new MostUsedWordStatistic();
        mostUsedWordStatistic.visit(new StringBatchData("Reading short stories in English is a great way to improve your language level. In this section, read our short stories that were specially written for English language learners. There are two sections, one for lower level learners (A2/B1) and one for higher levels (B2/C1)"));
        mostUsedWordStatistic.visit(new StringBatchData("You will improve your reading fluency and comprehension and develop your vocabulary. Each story has interactive exercises to help you understand and use the language."));
        assertThat(mostUsedWordStatistic.getMostUsedWord().orElse(""), is("and"));
    }

    @Test
    void mostUsedWord() {
        var mostUsedWordStatistic = new MostUsedWordStatistic();
        mostUsedWordStatistic.visit(new StringBatchData("AAA BBB AAA CCC"));
        assertThat(mostUsedWordStatistic.getMostUsedWord().orElse(""), is("AAA"));
    }

    @Test
    void emptyLine() {
        var mostUsedWordStatistic = new MostUsedWordStatistic();
        mostUsedWordStatistic.visit(new StringBatchData("     "));
        assertThat(mostUsedWordStatistic.getMostUsedWord().orElse(""), is(""));
    }
}