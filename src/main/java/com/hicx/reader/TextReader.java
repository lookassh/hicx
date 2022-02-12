package com.hicx.reader;

import com.hicx.batch.BatchData;
import com.hicx.batch.StringBatchData;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.function.Consumer;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class TextReader implements Reader{
    @NonNull
    private final Resource resource;
    @NonNull
    private final Charset charset;

    public TextReader(Resource resource) {
        this(resource, UTF_8);
    }

    public TextReader(Resource resource, Charset charset) {
        this.resource = resource;
        this.charset = charset;
    }

    @SneakyThrows
    @Override
    public void readInBatches(Consumer<BatchData> consumer){
        log.debug("Reading {} using {} charset", resource.getDescription(), charset.name());
        try(InputStream is = resource.getInputStream()){
            new BufferedReader(new InputStreamReader(is, charset)).lines()
                    .map(StringBatchData::new)
                    .forEach(consumer);
        }
        log.debug("Read finished");
    }

}
