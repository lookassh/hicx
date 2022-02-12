package com.hicx.reader;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReaderFactoryTest {
    private ReaderFactory readerFactory = new ReaderFactory();

    @Test
    void txtFile() throws IOException, URISyntaxException {
        Resource resource = mock(Resource.class);
        when(resource.getURI()).thenReturn(new URI("file.txt"));
        Optional<Reader> reader = readerFactory.createResourceReader(resource);
        assertThat(reader.isPresent(), is(true));
        assertThat(reader.get(), isA(TextReader.class));
    }

    @Test
    void pdfFile() throws IOException, URISyntaxException {
        Resource resource = mock(Resource.class);
        when(resource.getURI()).thenReturn(new URI("file.pdf"));
        Optional<Reader> reader = readerFactory.createResourceReader(resource);
        assertThat(reader.isPresent(), is(false));
    }
}