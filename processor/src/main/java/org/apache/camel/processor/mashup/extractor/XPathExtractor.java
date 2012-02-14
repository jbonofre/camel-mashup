package org.apache.camel.processor.mashup.extractor;

import org.apache.camel.processor.mashup.api.IExtractor;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

/**
 * Extractor using XPath.
 */
public class XPathExtractor implements IExtractor {

    private String path;
    
    public XPathExtractor() { }
    
    public String extract(String html) throws Exception {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        // TODO convert to xhtml using jtidy
        // TODO add xquery extractor
        // TODO add regex extractor

        return xPath.evaluate(path, html);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
