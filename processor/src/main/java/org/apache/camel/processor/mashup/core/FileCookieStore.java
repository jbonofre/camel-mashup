package org.apache.camel.processor.mashup.core;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

/**
 * Singleton to share a cookie store.
 */
public class FileCookieStore {

    private String fileStorePath;

    public FileCookieStore() throws Exception {
        fileStorePath = System.getProperty("cookiestore.file", System.getProperty("karaf.data") + "/cookies.properties");
        File file = new File(fileStorePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public synchronized void addCookie(String key, Cookie cookie) throws Exception {
        // load the cookies.properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(fileStorePath));
        // load the BasicCookieStore for the key
        BasicCookieStore cookieStore = this.unmarshalCookieStore(properties.getProperty(key));
        if (cookieStore == null) {
            cookieStore = new BasicCookieStore();
        }
        // add the cookie in the store
        cookieStore.addCookie(cookie);
        // serialize the cookie store
        properties.setProperty(key, marshalCookieStore(cookieStore));
        properties.store(new FileOutputStream(fileStorePath), null);
    }

    public synchronized List<org.apache.http.cookie.Cookie> getCookies(String key) throws Exception {
        // load the cookies.properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(fileStorePath));
        // load the BasicCookieStore for the key
        BasicCookieStore cookieStore = this.unmarshalCookieStore(properties.getProperty(key));
        if (cookieStore == null) {
            cookieStore = new BasicCookieStore();
        }
        return cookieStore.getCookies();
    }

    private synchronized BasicCookieStore unmarshalCookieStore(String data) throws Exception {
        if (data == null)
            return null;
        byte[] rawStore = Base64.decodeBase64(data);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(rawStore));
        BasicCookieStore cookieStore = (BasicCookieStore) ois.readObject();
        ois.close();
        return cookieStore;
    }

    private synchronized String marshalCookieStore(BasicCookieStore cookieStore) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(cookieStore);
        oos.flush();
        oos.close();
        return new String(Base64.encodeBase64(bos.toByteArray()));
    }

}
