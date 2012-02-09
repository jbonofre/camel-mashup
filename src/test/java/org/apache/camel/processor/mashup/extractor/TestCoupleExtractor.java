package org.apache.camel.processor.mashup.extractor;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Unit test on the couple extractor.
 */
public class TestCoupleExtractor {
    
    private String html;
    
    @Before
    public void loadHtml() throws Exception {
        html = FileUtils.readFileToString(new File("target/test-classes/extractor/content.html"));
    }
    
    @Test
    public void extract() throws Exception {
        CoupleExtractor extractor = new CoupleExtractor();
        extractor.setBegin("<table>");
        extractor.setEnd("</table>");
        String extracted = extractor.extract(html);
        assertNotNull(extracted);
        System.out.println(extracted);
    }
    
}
