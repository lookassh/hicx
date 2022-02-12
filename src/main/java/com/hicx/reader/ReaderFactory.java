package com.hicx.reader;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Optional.empty;

@Slf4j
@Component
public class ReaderFactory {

    @SneakyThrows
    public Optional<Reader> createResourceReader(@NonNull final Resource resource){
        String path = resource.getURI().getPath();
        if(path.endsWith(".txt")){
            return Optional.of(new TextReader(resource));
        }
        log.debug("Supported reader not found");
        return empty();
    }

}
