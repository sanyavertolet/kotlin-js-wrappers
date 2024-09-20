package io.github.sanyavertolet.jswrappers.cookie

import kotlin.test.*

class CookieTest {
    @Test
    fun cookieObjectIsAvailable() {
        assertNotNull(cookie)

        cookie.set("test-key", "test-value")
        assertEquals("test-value", cookie.get("test-key"))
        cookie.remove("test-key")
        assertNull(cookie.get("test-key"))
    }

    @Test
    fun cookieLanguageTest() {
        assertNotNull(cookie)
        assertFalse(cookie.isAccepted())
        cookie.acceptCookies()
        assertTrue(cookie.isAccepted())
        cookie.saveLanguageCode("en")
        assertEquals("en", cookie.getLanguageCode())
        cookie.declineCookies()
        assertFalse(cookie.isAccepted())
        assertNull(cookie.getLanguageCode())
    }
}
