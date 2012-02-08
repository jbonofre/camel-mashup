package org.apache.camel.processor.mashup.model;

import java.util.LinkedList;
import java.util.List;

public class Extractor {
    
    private String clazz;
    private boolean append = true;
    private List<Property> properties = new LinkedList<Property>();

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
    
    public void addProperty(Property property) {
        this.properties.add(property);
    }

}
