package com.sanyavertolet.jswrappers.http

import com.sanyavertolet.jswrappers.i18n.json
import com.sanyavertolet.jswrappers.i18n.plugins.BackendConfiguration
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.encodeToDynamic

/**
 * Configuration class for `i18next-http-backend` initialization.
 *
 * This class encapsulates all the configuration options available
 * for initializing `i18next-http-backend` in a Kotlin/JS environment.
 *
 * @see <a href="https://github.com/i18next/i18next-http-backend">i18next-http-backend configuration docs</a>
 */
@Suppress("unused")
@Serializable
class HttpBackendConfiguration : BackendConfiguration() {
    /**
     * Path where resources get loaded from.
     */
    var loadPath: String = "/locales/{{lng}}/{{ns}}.json"

    /**
     * Path to post missing resources.
     *
     * **Note: this only works when initialized with `{ saveMissing: true }`.**
     *
     * @see <a href="https://www.i18next.com/overview/configuration-options">i18next docs</a>
     */
    var addPath: String = "/locales/add/{{lng}}/{{ns}}"

    /**
     * Allows cross domain requests when true.
     */
    var crossDomain: Boolean = false

    /**
     * Allows credentials on cross domain requests .
     */
    var withCredentials: Boolean = false

    /**
     * Sets `request.overrideMimeType("application/json")`.
     */
    var overrideMimeType: Boolean = false

    /**
     * Sets `request.setRequestHeader(key, value)`
     * { authorization: 'foo', },
     */
    @Transient
    var customHeaders: dynamic = undefined

    /**
     * Adds query parameters to resource URL.
     */
    @Transient
    var queryStringParams: dynamic = undefined

    /**
     * Converts the interpolation configuration into a dynamic object suitable for `i18next` initialization.
     *
     * @return A dynamic object representing the interpolation configuration.
     */
    @OptIn(ExperimentalSerializationApi::class)
    override fun getAsDynamic(): dynamic {
        val configurationAsDynamic = json.encodeToDynamic(this)
        if (customHeaders != undefined) {
            configurationAsDynamic["customHeaders"] = customHeaders
        }
        if (queryStringParams != undefined) {
            configurationAsDynamic["queryStringParams"] = queryStringParams
        }
        return configurationAsDynamic
    }
}
