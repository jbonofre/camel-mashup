package org.apache.camel.processor.mashup.extractor;

import org.apache.camel.processor.mashup.api.IExtractor;

/**
 * A couple extractor that looking for start/stop pattern.
 */
public class CoupleExtractor implements IExtractor {
    
    private String begin;
    private String end;

    public CoupleExtractor() { }
    
    public String extract(String html) throws Exception {
        String content = null;
        int index = html.indexOf(begin);
        content = html.substring(index);
        index = content.indexOf(end);
        content = content.substring(0, index + end.length());
        return content;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}
