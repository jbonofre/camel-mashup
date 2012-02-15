package org.apache.camel.processor.mashup.core;

import org.apache.http.impl.client.BasicCookieStore;

/**
 * Singleton to share a cookie store.
 */
public class CookieStore {

    private static BasicCookieStore _singleton = null;

    public static BasicCookieStore getInstance() {
        if (_singleton == null) {
            _singleton = new BasicCookieStore();
        }
        return _singleton;
    }

}
