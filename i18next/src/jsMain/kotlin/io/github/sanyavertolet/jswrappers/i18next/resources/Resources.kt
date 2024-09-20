/**
 * File containing dsl for resources configuration
 */
package io.github.sanyavertolet.jswrappers.i18next.resources

import js.core.jso

/**
 * Represents the `resources` configuration for `i18next`.
 *
 * This class allows you to define translations for multiple languages
 * and their respective namespaces using a Kotlin DSL.
 */
class Resources internal constructor() {
    /**
     * Internal map holding language codes and their configurations.
     */
    private val languages: MutableMap<String, LanguageConfiguration> = mutableMapOf()

    /**
     * Adds a language configuration to the resources.
     *
     * @param language The language code (e.g., "en", "de").
     * @param languageBuilder A lambda to configure the [LanguageConfiguration].
     */
    fun language(language: String, languageBuilder: LanguageConfiguration.() -> Unit) {
        languages[language] = LanguageConfiguration().apply(languageBuilder)
    }

    /**
     * Converts the resources configuration into a dynamic object.
     *
     * This dynamic object can be passed to the JavaScript `i18next` `init` function.
     *
     * @return A dynamic object representing the resources.
     */
    internal fun getAsDynamic(): dynamic {
        val obj: dynamic = jso()
        languages.forEach { (key, language) -> obj[key] = language.getAsDynamic() }
        return obj
    }
}

/**
 * Extension function to conveniently add English (`en`) language resources.
 *
 * @param languageBuilder A lambda to configure the [LanguageConfiguration] for English.
 */
@Suppress("unused")
fun Resources.en(languageBuilder: LanguageConfiguration.() -> Unit) = language("en", languageBuilder)

/**
 * Extension function to conveniently add German (`de`) language resources.
 *
 * @param languageBuilder A lambda to configure the [LanguageConfiguration] for German.
 */
@Suppress("unused")
fun Resources.de(languageBuilder: LanguageConfiguration.() -> Unit) = language("de", languageBuilder)

/**
 * Extension function to conveniently add Spanish (`es`) language resources.
 *
 * @param languageBuilder A lambda to configure the [LanguageConfiguration] for Spanish.
 */
@Suppress("unused")
fun Resources.es(languageBuilder: LanguageConfiguration.() -> Unit) = language("es", languageBuilder)

/**
 * Extension function to conveniently add Italian (`it`) language resources.
 *
 * @param languageBuilder A lambda to configure the [LanguageConfiguration] for Italian.
 */
@Suppress("unused")
fun Resources.it(languageBuilder: LanguageConfiguration.() -> Unit) = language("it", languageBuilder)
