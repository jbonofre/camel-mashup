package org.apache.camel.processor.mashup.model;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test on the digester
 */
public class TestDigester {

    @Test
    public void digeste() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("target/test-classes/model/valid.xml");

        Mashup mashup = new Mashup();
        mashup.digeste(fileInputStream);

        assertEquals("test", mashup.getId());

        // get the pages
        List<Page> pages = mashup.getPages();
        assertEquals(pages.size(), 3);
        
        Page page1 = pages.get(0);
        assertEquals("http://www.example.org/page1", page1.getUrl());
        assertEquals("POST", page1.getMethod());
        
        Page page2 = pages.get(1);
        assertEquals("http://www.example.org/page2", page2.getUrl());
        
        // get page2 extractor
        List<Extractor> extractors = page2.getExtractors();
        assertEquals(1, extractors.size());
        
        // get the extractor
        Extractor extractor = extractors.get(0);
        assertEquals("my.class", extractor.getClazz());
        
        // get the extractor properties
        List<Property> properties = extractor.getProperties();
        assertEquals(1, properties.size());
        Property property = properties.get(0);
        assertEquals(property.getName(), "name1");
        assertEquals(property.getValue(), "value1");
        
        // get the page 3
        Page page3 = pages.get(2);
        assertEquals("http://www.example.org/page3", page3.getUrl());
        Extractor page3Extractor = page3.getExtractors().get(0);
        assertEquals("other.class", page3Extractor.getClazz());
        assertEquals(false, page3Extractor.isAppend());
        
        ErrorHandler errorHandler = page3.getErrorHandler();
        assertEquals(1, errorHandler.getExtractors().size());
        Extractor errorHandlerExtractor = errorHandler.getExtractors().get(0);
        assertEquals("my.class", errorHandlerExtractor.getClazz());
        
    }

}
