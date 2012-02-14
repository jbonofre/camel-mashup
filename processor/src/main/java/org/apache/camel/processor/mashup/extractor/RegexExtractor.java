package org.apache.camel.processor.mashup.extractor;

import org.apache.camel.processor.mashup.api.IExtractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extractor which uses a regex to extract data.
 */
public class RegexExtractor implements IExtractor {
    
    private String pattern;

    public RegexExtractor() { }
    
    public String extract(String html) throws Exception {
        Pattern p = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher m = p.matcher(html);

        if (!m.matches()) {
            return null;
        }

        return m.group(1);
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
}
