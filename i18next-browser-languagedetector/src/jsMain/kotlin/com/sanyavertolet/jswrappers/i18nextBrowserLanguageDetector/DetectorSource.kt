package com.sanyavertolet.jswrappers.i18nextBrowserLanguageDetector

import kotlinx.serialization.SerialName

/**
 * Enumeration of possible sources for language detection using `i18next` browser language detector.
 *
 * The browser language detector checks various sources to determine the user's preferred language.
 * This enum represents the different sources that can be used for language detection.
 */
@Suppress("unused")
enum class DetectorSource {
    /**
     * Language detected from the URL query string parameter.
     *
     * Example: `?lng=en` would set the language to English.
     */
    @SerialName("querystring")
    QUERYSTRING,

    /**
     * Language detected from a cookie stored in the browser.
     *
     * The detector looks for a cookie that specifies the language preference.
     */
    @SerialName("cookie")
    COOKIE,

    /**
     * Language detected from the browser's local storage.
     *
     * The language preference is stored in the local storage of the browser.
     */
    @SerialName("localStorage")
    LOCAL_STORAGE,

    /**
     * Language detected from the browser's session storage.
     *
     * Similar to local storage but data is cleared when the page session ends.
     */
    @SerialName("sessionStorage")
    SESSION_STORAGE,

    /**
     * Language detected from the browser's navigator object.
     *
     * Uses the language settings of the user's browser.
     */
    @SerialName("navigator")
    NAVIGATOR,

    /**
     * Language detected from the `lang` attribute of the `<html>` tag.
     *
     * Example: `<html lang="en">` would set the language to English.
     */
    @SerialName("htmlTag")
    HTML_TAG,

    /**
     * Language detected from the URL path.
     *
     * Example: A URL like `http://example.com/en/page` would set the language to English.
     */
    @SerialName("path")
    PATH,

    /**
     * Language detected from the subdomain of the URL.
     *
     * Example: A URL like `http://en.example.com` would set the language to English.
     */
    @SerialName("subdomain")
    SUBDOMAIN,
    ;

    companion object {

        /**
         * The default order in which the language detector will check the sources.
         *
         * By default, the detector checks the following sources in order:
         *
         * 1. **Query String**: Checks the URL for a `lng` parameter.
         * 2. **Cookie**: Checks for a language preference stored in cookies.
         * 3. **Local Storage**: Checks for a language preference stored in local storage.
         * 4. **Session Storage**: Checks for a language preference stored in session storage.
         * 5. **Navigator**: Uses the browser's language settings.
         * 6. **HTML Tag**: Checks the `lang` attribute of the `<html>` tag.
         *
         * These sources are checked sequentially until a language is detected.
         */
        val defaultSourceOrder = listOf(
            QUERYSTRING, COOKIE, LOCAL_STORAGE, SESSION_STORAGE, NAVIGATOR, HTML_TAG
        )

        /**
         * The default sources where the detected language will be cached.
         *
         * By default, the detector caches the language in:
         *
         * - **Local Storage**: Stores the detected language in the browser's local storage for persistent use across sessions.
         */
        val defaultSourceCaches = listOf(LOCAL_STORAGE)
    }
}