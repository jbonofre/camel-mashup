package org.apache.camel.processor.mashup.extractor;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * Unit test on the XPath extractor
 */
public class TestXPathExtractor {

    private String html;

    @Before
    public void loadHtml() throws Exception {
        html = FileUtils.readFileToString(new File("target/test-classes/extractor/content.html"));
    }    
    
    @Test
    public void extract() throws Exception {
        XPathExtractor extractor = new XPathExtractor();
        extractor.setPath("//table");
        String extracted = extractor.extract(html);
        System.out.println(extracted);
    }
    
}
