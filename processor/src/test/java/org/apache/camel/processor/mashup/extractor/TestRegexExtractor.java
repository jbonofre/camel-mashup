package org.apache.camel.processor.mashup.extractor;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

/**
 * Unit test on the regex extractor.
 */
public class TestRegexExtractor {

    private String html;

    @Before
    public void loadHtml() throws Exception {
        html = FileUtils.readFileToString(new File("target/test-classes/extractor/content.html"));
    }

    @Test
    public void extract() throws Exception {
        RegexExtractor extractor = new RegexExtractor();
        extractor.setPattern(".*<table>(.*)</table>.*");
        String extracted = extractor.extract(html);
        assertNotNull(extracted);
        System.out.println(extracted);
    }    
    
}
