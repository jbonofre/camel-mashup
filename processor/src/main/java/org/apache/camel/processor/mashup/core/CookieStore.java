package org.apache.camel.processor.mashup.core;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.*;

/**
 * Singleton to share a cookie store.
 */
public class CookieStore {
    
    private Map<String, List<Cookie>> map;
    
    private static CookieStore _singleton = null;

    public static CookieStore getInstance() {
        if (_singleton == null) {
            _singleton = new CookieStore();
        }
        return _singleton;
    }
    
    private CookieStore() {
        map = new HashMap<String, List<org.apache.http.cookie.Cookie>>();
    }
    
    public void addCookie(String key, org.apache.http.cookie.Cookie cookie) {
        List<org.apache.http.cookie.Cookie> cookies = this.getCookies(key);
        if (cookies == null) {
            cookies = new LinkedList<org.apache.http.cookie.Cookie>();
        }
        cookies.add(cookie);
        map.put(key, cookies);
    }
    
    public List<org.apache.http.cookie.Cookie> getCookies(String key) {
        return map.get(key);
    }

}
