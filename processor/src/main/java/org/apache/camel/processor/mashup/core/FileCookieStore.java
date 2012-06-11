package org.apache.camel.processor.mashup.core;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

public class FileCookieStore {

    private String fileStorePath;

    public FileCookieStore(String key) throws Exception {
        File fileStoreDir = new File(System.getProperty("cookiestore.dir", System.getProperty("karaf.data") + "/cookies"));
        fileStoreDir.mkdirs();
        fileStorePath = fileStoreDir + "/" + key;
    }

    public synchronized void addCookie(Cookie cookie) throws Exception {
        File file = new File(fileStorePath);
        BasicCookieStore cookieStore = null;
        if (file.exists()) {
            // load the cookies file
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileStorePath));
            cookieStore = (BasicCookieStore) ois.readObject();
        }
        if (cookieStore == null) {
            cookieStore = new BasicCookieStore();
        }
        // add the cookie in the store
        cookieStore.addCookie(cookie);
        // serialize the cookie store
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileStorePath));
        oos.writeObject(cookieStore);
        oos.flush();
        oos.close();
    }

    public synchronized List<org.apache.http.cookie.Cookie> getCookies() throws Exception {
        // load the cookies file
        File file = new File(fileStorePath);
        BasicCookieStore cookieStore = null;
        if (file.exists()) {
            // load the cookies file
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileStorePath));
            cookieStore = (BasicCookieStore) ois.readObject();
        }
        if (cookieStore == null) {
            cookieStore = new BasicCookieStore();
        }
        // return the cookies
        return cookieStore.getCookies();
    }

}
