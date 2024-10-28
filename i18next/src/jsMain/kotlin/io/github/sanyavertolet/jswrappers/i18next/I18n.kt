/**
 * File containing a new way of initialization of `i18next`
 */

package io.github.sanyavertolet.jswrappers.i18next

import kotlinext.js.require
import kotlin.js.Promise

/**
 * Type alias for the translation function.
 *
 * Represents a function that takes a [String] key and returns a translated [String].
 */
typealias TranslationFunction = (String) -> String

/**
 * Type alias for the translation function with String receiver.
 *
 * Represents an extension function that returns a translated [String].
 */
typealias TranslationFunctionWithReceiver = String.() -> String

/**
 * Represents the `i18next` instance.
 *
 * This external class corresponds to the `i18next` JavaScript library's main object.
 */
@Suppress("unused")
external class I18n {
    /**
     * The current language code in use.
     */
    val language: String

    /**
     * Changes the current language.
     *
     * @param language The language code to switch to.
     */
    fun changeLanguage(language: String)

    /**
     * Uses a plugin or module with the `i18next` instance.
     *
     * @param module The module to integrate with `i18next`.
     * @return The [I18n] instance for chaining.
     */
    fun use(module: dynamic): I18n

    /**
     * Checks if a translation key exists.
     *
     * @param key The translation key to verify.
     * @return `true` if the key exists, `false` otherwise.
     */
    @Suppress("FUNCTION_BOOLEAN_PREFIX")
    fun exists(key: String): Boolean

    /**
     * Translates a given key.
     *
     * @param key The translation key.
     * @return The translated string.
     */
    @Suppress("IDENTIFIER_LENGTH")
    fun t(key: String): String

    /**
     * Checks if a namespace has been loaded.
     *
     * @param namespace The namespace to check.
     * @return `true` if the namespace is loaded, `false` otherwise.
     */
    fun hasLoadedNamespace(namespace: String): Boolean

    /**
     * Initializes the `i18next` instance with the given configuration.
     *
     * **Deprecated:** Use [init] instead for type-safe configuration.
     *
     * @param configuration The dynamic configuration object.
     * @param callback Optional callback function executed after initialization.
     * @return A [Promise] resolving to the [TranslationFunction].
     */
    @JsName("init")
    @Deprecated("Use init instead as it is type-safe", ReplaceWith("init()"))
    @Suppress("TYPE_ALIAS")
    fun initInternal(
        configuration: dynamic,
        callback: (dynamic, dynamic) -> Unit = definedExternally,
    ): Promise<TranslationFunction>
}

/**
 * Initializes the `i18next` instance with a type-safe configuration.
 *
 * @param configurationBuilder A lambda to configure [I18nConfiguration].
 * @return A [Promise] resolving to the [TranslationFunction].
 */
fun I18n.init(configurationBuilder: I18nConfiguration.() -> Unit): Promise<TranslationFunction> {
    val configuration = I18nConfiguration().apply(configurationBuilder).getAsDynamic()
    configuration["backend"] = asDynamic()["backend"]
    configuration["detection"] = asDynamic()["detection"]
    configuration["cache"] = asDynamic()["cache"]
    @Suppress("DEPRECATION")
    return initInternal(configuration)
}

/**
 * Loads the `i18next` module.
 *
 * @return The [I18n] instance from the `i18next` library.
 */
@Suppress("unused")
fun requireI18next(): I18n = require("i18next")
