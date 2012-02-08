package org.apache.camel.processor.mashup.model;

import java.util.LinkedList;

public class Page {
    
    private String url;
    private String method;
    private LinkedList<Extractor> extractors = new LinkedList<Extractor>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinkedList<Extractor> getExtractors() {
        return extractors;
    }

    public void setExtractors(LinkedList<Extractor> extractors) {
        this.extractors = extractors;
    }
    
    public void addExtractor(Extractor extractor) {
        this.extractors.add(extractor);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
