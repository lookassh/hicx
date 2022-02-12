package com.hicx.job;

import com.hicx.reader.ReaderFactory;
import com.hicx.scanner.DirectoryScanner;
import com.hicx.stat.CompositeStatistic;
import com.hicx.stat.MostUsedWordStatistic;
import com.hicx.stat.NumOfWordsStatistic;
import com.hicx.stat.StringOccurrenceStatistic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class AnalyseFileJobConfiguration {

    @Scope(SCOPE_PROTOTYPE)
    @Bean
    public CompositeStatistic statistic() {
        return new CompositeStatistic(Arrays.asList(
                new MostUsedWordStatistic(),
                new NumOfWordsStatistic(),
                new StringOccurrenceStatistic("."))
        );
    }

    @Scope(SCOPE_PROTOTYPE)
    @Bean
    public DirectoryScanner directoryScanner(@Value("${scan.directory}") Path scanDirectory) throws IOException {
        return new DirectoryScanner(scanDirectory);
    }

    @Scope(SCOPE_PROTOTYPE)
    @Bean
    public AnalyseFileJob analyseFileJob(ReaderFactory readerFactory, DirectoryScanner directoryScanner) {
        return new AnalyseFileJob(directoryScanner, readerFactory, statistic());
    }

}
