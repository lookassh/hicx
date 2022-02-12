package com.hicx.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AnalyseFileJobScheduler {

    private final ObjectProvider<AnalyseFileJob> analyseFileJobsProvider;

    @Scheduled(fixedDelayString = "${scan.fixed-delay}")
    public void run() throws IOException {
        analyseFileJobsProvider.getObject().run();
    }

}
