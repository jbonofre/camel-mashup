package org.apache.camel.processor.mashup.core;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFileCookieStore {

    @Test
    public void serialize() throws Exception {
        System.setProperty("karaf.data", "target");

        FileCookieStore fileCookieStore = new FileCookieStore();
        BasicClientCookie cookie1 = new BasicClientCookie("test", "test");
        BasicClientCookie cookie2 = new BasicClientCookie("other", "other");
        fileCookieStore.addCookie("test", cookie1);
        fileCookieStore.addCookie("test", cookie2);

        assertEquals(2, fileCookieStore.getCookies("test").size());

        Cookie cookieTest =  fileCookieStore.getCookies("test").get(1);
        assertNotNull(cookieTest);
        assertEquals("test", cookieTest.getName());
        assertEquals("test", cookieTest.getValue());

        cookieTest = fileCookieStore.getCookies("test").get(0);
        assertNotNull(cookieTest);
        assertEquals("other", cookieTest.getName());
        assertEquals("other", cookieTest.getValue());
    }

}
