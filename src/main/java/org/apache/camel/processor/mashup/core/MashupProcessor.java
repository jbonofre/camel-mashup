package org.apache.camel.processor.mashup.core;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.processor.mashup.model.Mashup;
import org.apache.camel.processor.mashup.model.Page;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

/**
 * Camel processor loading the navigation file and extract data
 */
public class MashupProcessor implements Processor {
    
    private final static transient Logger LOGGER = LoggerFactory.getLogger(MashupProcessor.class);
    
    private String store;
    
    public void process(Exchange exchange) throws Exception {
        LOGGER.trace("Get the Camel in message");
        Message in = exchange.getIn();
        
        LOGGER.trace("Get the MASHUP_ID header");
        String mashupId = (String) in.getHeader("MASHUP_ID");
        LOGGER.debug("Mashup ID: " + mashupId);
        
        LOGGER.debug("Digesting the navigation file " + store + "/" + mashupId + ".xml");
        FileInputStream fileInputStream = new FileInputStream(store + "/" + mashupId + ".xml");
        Mashup mashup = new Mashup();
        mashup.digeste(fileInputStream);
        
        LOGGER.trace("Create the HTTP client");
        HttpClient httpClient = new DefaultHttpClient();

        // TODO check for existing session
        
        LOGGER.trace("Iterate in the pages");
        for (Page page : mashup.getPages()) {
            
        }
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

}
