package org.apache.camel.processor.mashup.core;

import org.apache.camel.processor.mashup.api.IExtractor;
import org.apache.camel.processor.mashup.extractor.CoupleExtractor;
import org.apache.camel.processor.mashup.extractor.JsoupExtractor;
import org.apache.camel.processor.mashup.extractor.XPathExtractor;
import org.apache.camel.processor.mashup.model.Extractor;
import org.apache.camel.processor.mashup.model.Property;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test on the creation of a unit test
 */
public class TestExtractorInstantiation {
    
    @Test
    public void instantiateCoupleExtractor() throws Exception {
        Extractor extractor = new Extractor();
        extractor.setClazz("org.apache.camel.processor.mashup.extractor.CoupleExtractor");
        Property begin = new Property();
        begin.setName("begin");
        begin.setValue("<table>");
        extractor.addProperty(begin);
        Property end = new Property();
        end.setName("end");
        end.setValue("</table>");
        extractor.addProperty(end);

        MashupProcessor processor = new MashupProcessor();
        IExtractor extractorBean = processor.instantiateExtractor(extractor);
        
        assertNotNull(extractorBean);
        assertTrue(extractorBean instanceof CoupleExtractor);

        CoupleExtractor coupleExtractor = (CoupleExtractor) extractorBean;
        assertEquals("<table>", coupleExtractor.getBegin());
        assertEquals("</table>", coupleExtractor.getEnd());
    }
    
    @Test
    public void instantiateJsoupExtractor() throws Exception {
        Extractor extractor = new Extractor();
        extractor.setClazz("org.apache.camel.processor.mashup.extractor.JsoupExtractor");
        Property query = new Property();
        query.setName("query");
        query.setValue("div");
        extractor.addProperty(query);
        
        MashupProcessor processor = new MashupProcessor();
        IExtractor extractorBean = processor.instantiateExtractor(extractor);
        
        assertNotNull(extractorBean);
        assertTrue(extractorBean instanceof JsoupExtractor);
        
        JsoupExtractor jsoupExtractor = (JsoupExtractor) extractorBean;
        assertEquals("div", jsoupExtractor.getQuery());
    }
    
    @Test
    public void instantiateXPathExtractor() throws Exception {
        Extractor extractor = new Extractor();
        extractor.setClazz("org.apache.camel.processor.mashup.extractor.XPathExtractor");
        Property path = new Property();
        path.setName("path");
        path.setValue("//div");
        extractor.addProperty(path);
        
        MashupProcessor processor = new MashupProcessor();
        IExtractor extractorBean = processor.instantiateExtractor(extractor);
        
        assertNotNull(extractorBean);
        assertTrue(extractorBean instanceof XPathExtractor);
        
        XPathExtractor xPathExtractor = (XPathExtractor) extractorBean;
        assertEquals("//div", xPathExtractor.getPath());
    }
    
}
