package org.apache.camel.processor.mashup.core;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFileCookieStore {

    @Test
    public void serialize() throws Exception {
        System.setProperty("karaf.data", "target");

        FileCookieStore testFileCookieStore = new FileCookieStore("test");
        FileCookieStore otherFileCookieStore = new FileCookieStore("other");

        BasicClientCookie cookie1 = new BasicClientCookie("test", "test");
        BasicClientCookie cookie2 = new BasicClientCookie("other", "other");

        testFileCookieStore.addCookie(cookie1);
        testFileCookieStore.addCookie(cookie2);
        otherFileCookieStore.addCookie(cookie1);
        otherFileCookieStore.addCookie(cookie2);

        assertEquals(2, testFileCookieStore.getCookies().size());
        assertEquals(2, otherFileCookieStore.getCookies().size());

        Cookie cookieTest =  testFileCookieStore.getCookies().get(1);
        assertNotNull(cookieTest);
        assertEquals("test", cookieTest.getName());
        assertEquals("test", cookieTest.getValue());

        cookieTest = testFileCookieStore.getCookies().get(0);
        assertNotNull(cookieTest);
        assertEquals("other", cookieTest.getName());
        assertEquals("other", cookieTest.getValue());
    }

}
