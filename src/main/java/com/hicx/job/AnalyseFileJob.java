package com.hicx.job;

import com.hicx.reader.ReaderFactory;
import com.hicx.scanner.DirectoryScanner;
import com.hicx.stat.MostUsedWordStatistic;
import com.hicx.stat.NumOfWordsStatistic;
import com.hicx.stat.StringOccurrenceStatistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@Component
public class AnalyseFileJob {
    private final ReaderFactory readerFactory;
    private final Path scanDirectory;

    public AnalyseFileJob(ReaderFactory readerFactory, @Value("${scan.directory}") Path scanDirectory) {
        this.readerFactory = readerFactory;
        this.scanDirectory = scanDirectory;

    }

    @Scheduled(fixedDelayString = "${scan.fixed-delay}")
    public void run() throws IOException {
        DirectoryScanner directoryScanner = new DirectoryScanner(scanDirectory);
        directoryScanner.scan(scannerItem -> {

            readerFactory.createResourceReader(scannerItem.getResource())
                    .ifPresent(reader -> {

                        var mostUsedWordStatistic = new MostUsedWordStatistic();
                        var numOfWordsStatistic = new NumOfWordsStatistic();
                        var dotsOccurrenceStatistic = new StringOccurrenceStatistic(".");

                        reader.readInBatches(batchData -> {
                            batchData.accept(mostUsedWordStatistic);
                            batchData.accept(numOfWordsStatistic);
                            batchData.accept(dotsOccurrenceStatistic);
                        });

                        log.info("Statistics for {} file", scannerItem.getResource().getFilename());
                        log.info("Most used word: {}", mostUsedWordStatistic.getMostUsedWord().orElse(""));
                        log.info("Number of words: {}", numOfWordsStatistic.getCount());
                        log.info("Number of dots: {}", dotsOccurrenceStatistic.getCount());

                        scannerItem.markAsProcessed();
                    });

        });

    }

}
