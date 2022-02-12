package com.hicx.job;

import com.hicx.reader.ReaderFactory;
import com.hicx.scanner.DirectoryScanner;
import com.hicx.stat.MostUsedWordStatistic;
import com.hicx.stat.NumOfWordsStatistic;
import com.hicx.stat.Statistic;
import com.hicx.stat.StringOccurrenceStatistic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
public class AnalyseFileJob {
    private final DirectoryScanner directoryScanner;
    private final ReaderFactory readerFactory;
    private final Statistic statistic;

    @Scheduled(fixedDelayString = "${scan.fixed-delay}")
    public void run() throws IOException {
        directoryScanner.scan(scannerItem -> {
            readerFactory.createResourceReader(scannerItem.getResource())
                    .ifPresent(reader -> {

                        reader.readInBatches(batchData -> {
                            batchData.accept(statistic);
                        });

                        log.info("Statistics for {} file", scannerItem.getResource().getFilename());
                        statistic.print();

                        scannerItem.markAsProcessed();
                    });
        });

    }

}
