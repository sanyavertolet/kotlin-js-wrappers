/**
 * File containing a new way of initialization of `i18next-http-backend`
 */

package io.github.sanyavertolet.jswrappers.i18nexthttpbackend

import io.github.sanyavertolet.jswrappers.i18next.I18n

private val httpBackendPlugin: dynamic = kotlinext.js.require("i18next-http-backend")

/**
 * Uses a `i18next-http-backend` plugin with the `i18next` instance.
 *
 * @receiver The [I18n] instance to which the http backend plugin will be added.
 * @param configurationBuilder lambda that is used to configure the backend plugin.
 * @return The [I18n] instance for chaining.
 */
@Suppress("unused")
fun I18n.useHttpBackendPlugin(
    configurationBuilder: HttpBackendConfiguration.() -> Unit = {},
): I18n = use(httpBackendPlugin.default).also {
    asDynamic()["backend"] = HttpBackendConfiguration().apply(configurationBuilder).getAsDynamic()
}
