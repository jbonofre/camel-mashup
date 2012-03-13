package org.apache.camel.processor.mashup.extractor;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Unit test on the jsoup extractor.
 */
public class TestJsoupExtractor {
    
    private String html;
    
    @Before
    public void loadHtml() throws Exception {
        html = FileUtils.readFileToString(new File("target/test-classes/extractor/content.html"));
    }
    
    @Test
    public void extract() throws Exception {
        JsoupExtractor extractor = new JsoupExtractor();
        extractor.setQuery("div#table");
        String extracted = extractor.extract(html);
        assertNotNull(extracted);
        System.out.println(extracted);
    }
    
}
