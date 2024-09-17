package com.sanyavertolet.jswrappers.i18nextBrowserLanguageDetector

import com.sanyavertolet.jswrappers.i18next.json
import com.sanyavertolet.jswrappers.i18next.plugins.DetectionConfiguration
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.encodeToDynamic

/**
 * Configuration class for `i18next-browser-langaugedetector` initialization.
 *
 * This class encapsulates all the configuration options available
 * for initializing `i18next-browser-langaugedetector` in a Kotlin/JS environment.
 *
 * @see <a href="https://github.com/i18next/i18next-browser-languageDetector">i18next-browser-languageDetector docs</a>
 */
@Suppress("unused")
@Serializable
class BrowserLanguageDetectorConfiguration : DetectionConfiguration() {
    /**
     * Order and source of language detection.
     *
     * Defaults to [DetectorSource.defaultSourceOrder].
     *
     * @see DetectorSource.defaultSourceOrder
     */
    var order: List<DetectorSource> = DetectorSource.defaultSourceOrder

    /**
     * Querystring lookup key.
     *
     * Defaults to `"lng"`.
     */
    var lookupQuerystring: String = "lng"

    /**
     * Cookie lookup key.
     *
     * Defaults to `"i18next"`.
     */
    var lookupCookie: String = "i18next"

    /**
     * Local storage lookup key.
     *
     * Defaults to `"i18nextLng"`.
     */
    var lookupLocalStorage: String = "i18nextLng"

    /**
     * Session storage lookup key.
     *
     * Defaults to `"i18nextLng"`.
     */
    var lookupSessionStorage: String = "i18nextLng"

    /**
     * Index of path element that should be treated as lookup key.
     *
     * Defaults to `0`.
     */
    var lookupFromPathIndex: Int = 0

    /**
     * Index of subdomain that should be treated as lookup key (left-to-right).
     *
     * Defaults to `0`.
     */
    var lookupFromSubdomainIndex: Int = 0

    /**
     * cache user language on
     *
     * Defaults to [DetectorSource.defaultSourceCaches]
     *
     * @see DetectorSource.defaultSourceCaches
     */
    var caches: List<DetectorSource> = DetectorSource.defaultSourceCaches

    /**
     * languages to not persist (cookie, localStorage)
     *
     * Defaults to `cimode`
     */
    var excludeCacheFor: List<String> = listOf("cimode")

    /**
     * Optional expiry for cookie.
     */
    var cookieMinutes: Int? = null

    /**
     * Optional domain for cookie.
     */
    var cookieDomain: String? = null

    /**
     * Optional htmlTag with lang attribute.
     *
     * Defaults to `"document.documentElement"`.
     */
    var htmlTag: String = "document.documentElement"

    /**
     * Optional cookie options.
     * { path: '/', sameSite: 'strict' }
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Set-Cookie">MDN Set-Cookie docs</a>
     */
    @Transient
    private var cookieOptions: dynamic = undefined

    /**
     * Optional conversion function used to modify the detected language code.
     * 'Iso15897'
     */
    var convertDetectedLanguage: String = "Iso15897"

    /**
     * Configures the `cookieOptions` using a dynamic object.
     *
     * **Warning: no type checks provided with [cookieOptions].**
     *
     * @param optionsObject A dynamic `cookieOptions` object.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun cookieOptions(optionsObject: dynamic) {
        this.cookieOptions = optionsObject
    }

    /**
     * Converts the `detection` configuration into a dynamic object suitable for `i18next` initialization.
     *
     * @return A dynamic object representing the `detection` configuration.
     */
    @OptIn(ExperimentalSerializationApi::class)
    override fun getAsDynamic(): dynamic {
        val configurationAsDynamic = json.encodeToDynamic(this)
        if (cookieOptions != undefined) {
            configurationAsDynamic["cookieOptions"] = cookieOptions
        }
        return configurationAsDynamic
    }
}
