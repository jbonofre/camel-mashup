package org.apache.camel.processor.mashup.model;

import java.util.LinkedList;
import java.util.List;

public class Page {
    
    private String url;
    private String method;
    private int wait;
    private List<Extractor> extractors = new LinkedList<Extractor>();
    private ErrorHandler errorHandler = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Extractor> getExtractors() {
        return extractors;
    }

    public void setExtractors(List<Extractor> extractors) {
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

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

}
