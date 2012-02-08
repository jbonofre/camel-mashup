package org.apache.camel.processor.mashup.core;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.processor.mashup.api.IExtractor;
import org.apache.camel.processor.mashup.model.Extractor;
import org.apache.camel.processor.mashup.model.Mashup;
import org.apache.camel.processor.mashup.model.Page;
import org.apache.camel.processor.mashup.model.Property;
import org.apache.commons.beanutils.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
        
        LOGGER.trace("Get the Camel out message");
        Message out = exchange.getOut();
        
        LOGGER.trace("Get the MASHUP_ID header");
        String mashupId = (String) in.getHeader("MASHUP_ID");
        LOGGER.debug("Mashup ID: " + mashupId);
        
        LOGGER.debug("Digesting the navigation file " + store + "/" + mashupId + ".xml");
        FileInputStream fileInputStream = new FileInputStream(store + "/" + mashupId + ".xml");
        Mashup mashup = new Mashup();
        mashup.digeste(fileInputStream);
        
        LOGGER.trace("Create the HTTP client");
        HttpClient httpClient = new DefaultHttpClient();

        // TODO check for existing session using a CookieStore
        
        LOGGER.trace("Iterate in the pages");
        for (Page page : mashup.getPages()) {
            LOGGER.trace("Replacing the headers in the URL");
            String url = page.getUrl();
            for (String header : in.getHeaders().keySet()) {
                url.replace("%" + header + "%", (String) in.getHeader(header));
            }
            
            LOGGER.trace("Constructing the HTTP request");
            HttpUriRequest request = null;
            if (page.getMethod().equalsIgnoreCase("POST")) {
                request = new HttpPost(url);
            } else {
                request = new HttpGet(url);
            }
            
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            
            if (page.getExtractors() != null && page.getExtractors().size() > 0) {
                LOGGER.trace("Populate content to be used by extractors");
                String content = EntityUtils.toString(entity);
                for (Extractor extractor : page.getExtractors()) {
                    Class extractorClass = Class.forName(extractor.getClazz());
                    IExtractor extractorBean = (IExtractor) extractorClass.newInstance();
                    for (Property property : extractor.getProperties()) {
                        PropertyUtils.setProperty(extractorBean, property.getName(), property.getValue());
                    }
                    String extractedData = extractorBean.extract(content);
                    if (extractor.isAppend()) {
                        out.setBody(out.getBody() + extractedData);
                    }
                }

            }

        }
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

}
