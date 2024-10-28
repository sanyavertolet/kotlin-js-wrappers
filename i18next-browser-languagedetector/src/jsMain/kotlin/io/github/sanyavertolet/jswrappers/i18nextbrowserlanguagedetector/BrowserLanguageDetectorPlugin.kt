/**
 * File containing a new way of initialization of `i18next-browser-langaugedetector`
 */

package io.github.sanyavertolet.jswrappers.i18nextbrowserlanguagedetector

import io.github.sanyavertolet.jswrappers.i18next.I18n

/**
 * Dynamically imports the `i18next-browser-languagedetector` module.
 *
 * This private dynamic variable holds the browser language detector module required at runtime.
 */
private val browserLanguageDetector: dynamic = kotlinext.js.require("i18next-browser-languagedetector")

/**
 * Integrates the `i18next` instance with the browser language detector.
 *
 * This extension function allows the `i18next` instance to use the `i18next-browser-languagedetector` plugin.
 * It enables automatic detection of the user's preferred language based on the browser's settings.
 *
 * @receiver The [I18n] instance to which the browser language detector will be added.
 * @param configurationBuilder lambda that is used to configure the language detector plugin.
 * @return The [I18n] instance for chaining.
 */
@Suppress("unused")
fun I18n.useBrowserLanguageDetector(
    configurationBuilder: BrowserLanguageDetectorConfiguration.() -> Unit = {}
) = use(browserLanguageDetector.default).also {
    asDynamic()["detection"] = BrowserLanguageDetectorConfiguration().apply(configurationBuilder).getAsDynamic()
}
