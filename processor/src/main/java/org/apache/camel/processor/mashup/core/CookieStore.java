package org.apache.camel.processor.mashup.core;

import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton to share a cookie store.
 */
public class CookieStore {
    
    private Map<String, org.apache.http.cookie.Cookie> map;
    
    private static CookieStore _singleton = null;

    public static CookieStore getInstance() {
        if (_singleton == null) {
            _singleton = new CookieStore();
        }
        return _singleton;
    }
    
    private CookieStore() {
        map = new HashMap<String, org.apache.http.cookie.Cookie>();
    }
    
    public void addCookie(String key, org.apache.http.cookie.Cookie cookie) {
        map.put(key, cookie);
    }
    
    public org.apache.http.cookie.Cookie getCookie(String key) {
        return map.get(key);
    }

}
