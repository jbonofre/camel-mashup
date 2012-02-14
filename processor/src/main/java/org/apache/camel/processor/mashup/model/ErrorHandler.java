package org.apache.camel.processor.mashup.model;

import java.util.LinkedList;
import java.util.List;

public class ErrorHandler {
    
    private List<Extractor> extractors = new LinkedList<Extractor>();

    public List<Extractor> getExtractors() {
        return extractors;
    }

    public void setExtractors(List<Extractor> extractors) {
        this.extractors = extractors;
    }
    
    public void addExtractor(Extractor extractor) {
        this.extractors.add(extractor);
    }

}
