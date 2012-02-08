package org.apache.camel.processor.mashup.model;

import java.util.LinkedList;

public class Extractor {
    
    private String clazz;
    private LinkedList<Property> properties = new LinkedList<Property>();

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public LinkedList<Property> getProperties() {
        return properties;
    }

    public void setProperties(LinkedList<Property> properties) {
        this.properties = properties;
    }
    
    public void addProperty(Property property) {
        this.properties.add(property);
    }

}
