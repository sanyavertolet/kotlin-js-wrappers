@file:Suppress("HEADER_MISSING_IN_NON_SINGLE_CLASS_FILE")

package io.github.sanyavertolet.jswrappers.cookie

import js.core.jso
import kotlinext.js.require

/**
 * Object that manages cookies
 */
val cookie: Cookie = require("js-cookie")

/**
 * Interface that encapsulates all cookies interactions
 */
external interface Cookie {
    /**
     * Get cookie by [key]
     *
     * @param key key to get cookie
     * @param cookieAttribute [CookieAttribute]
     * @return cookie as [String]
     */
    fun get(key: String, cookieAttribute: CookieAttribute = definedExternally): String?

    /**
     * Set cookie
     *
     * @param key key to set cookie
     * @param value cookie value as [String]
     * @param cookieAttribute [CookieAttribute]
     */
    fun set(key: String, value: String, cookieAttribute: CookieAttribute = definedExternally)

    /**
     * Remove cookie
     *
     * @param key cookie key
     * @param cookieAttribute [CookieAttribute]
     */
    fun remove(key: String, cookieAttribute: CookieAttribute = definedExternally)
}

/**
 * @param key key as [CookieKeys]
 * @param value value to set
 * @see Cookie.set
 */
fun Cookie.set(key: CookieKeys, value: String) = set(key.key, value, jso { expires = key.expires })

/**
 * @param key key as [CookieKeys]
 * @return cookie as [String] by [CookieKeys.key] of [key]
 * @see Cookie.get
 */
fun Cookie.get(key: CookieKeys): String? = get(key.key).takeIf { it != undefined }

/**
 * @param key key as [CookieKeys]
 * @see Cookie.remove
 */
fun Cookie.remove(key: CookieKeys) = remove(key.key)

/**
 * Check if cookies are accepted by user
 *
 * @return true if cookies are accepted, false otherwise
 */
fun Cookie.isAccepted() = get(CookieKeys.IsCookieOk) == "true"

/**
 * Accept cookies
 *
 * @return [Unit]
 */
fun Cookie.acceptCookies() = set(CookieKeys.IsCookieOk, "true")

/**
 * Decline cookies and delete existed once
 *
 * @return [Unit]
 */
fun Cookie.declineCookies() = remove(CookieKeys.IsCookieOk).also {
    remove(CookieKeys.PreferredLanguage)
}

/**
 * Get preferred platform language code
 *
 * @return preferred platform language code as [String]
 */
fun Cookie.getLanguageCode(): String? = get(CookieKeys.PreferredLanguage)

/**
 * Save preferred platform language code
 *
 * @param languageCode preferred platform language code as [String]
 * @return [Unit]
 */
fun Cookie.saveLanguageCode(languageCode: String) = set(CookieKeys.PreferredLanguage, languageCode)
