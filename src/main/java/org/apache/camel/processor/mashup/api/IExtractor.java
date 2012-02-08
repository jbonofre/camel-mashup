package org.apache.camel.processor.mashup.api;

/**
 * Interface describing an extractor
 */
public interface IExtractor {

    /**
     * Extract data from a website page.
     *
     * @param html the HTML source code of the page.
     * @return the extracted data.
     * @throws Exception in case of extraction failure.
     */
    public String extract(String html) throws Exception;
    
}
