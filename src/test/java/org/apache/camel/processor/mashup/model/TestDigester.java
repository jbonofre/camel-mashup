package org.apache.camel.processor.mashup.model;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.LinkedList;

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
        LinkedList<Page> pages = mashup.getPages();
        assertEquals(pages.size(), 2);
        
        Page page1 = pages.get(0);
        assertEquals("http://www.example.org/page1", page1.getUrl());
        assertEquals("POST", page1.getMethod());
        
        Page page2 = pages.get(1);
        assertEquals("http://www.example.org/page2", page2.getUrl());
        
        // get page2 extractor
        LinkedList<Extractor> extractors = page2.getExtractors();
        assertEquals(extractors.size(), 1);
        
        // get the extractor
        Extractor extractor = extractors.get(0);
        assertEquals("my.class", extractor.getClazz());
        
        // get the extractor properties
        LinkedList<Property> properties = extractor.getProperties();
        assertEquals(1, properties.size());
        Property property = properties.get(0);
        assertEquals(property.getName(), "name1");
        assertEquals(property.getValue(), "value1");
    }

}
