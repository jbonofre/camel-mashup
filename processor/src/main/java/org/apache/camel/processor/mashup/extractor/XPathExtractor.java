package org.apache.camel.processor.mashup.extractor;

import org.apache.camel.processor.mashup.api.IExtractor;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

/**
 * Extractor using XPath.
 */
public class XPathExtractor implements IExtractor {

    private String path;
    
    public XPathExtractor() { }
    
    public String extract(String html) throws Exception {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        InputSource source = new InputSource(new StringReader(html));

        return xPath.evaluate(path, source, XPathConstants.STRING).toString();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
