package io.github.sanyavertolet.jswrappers.i18next.resources

import js.objects.jso

/**
 * Represents the configuration for a specific language in `i18next`.
 *
 * This class allows to define multiple namespaces and their translations
 * for a particular language using a Kotlin DSL.
 */
class LanguageConfiguration internal constructor() {
    /**
     * Internal map holding namespace names and their corresponding translations.
     */
    private val namespaces: MutableMap<String, TranslationConfiguration> = mutableMapOf()

    /**
     * Adds a namespace and its translations to the language configuration.
     *
     * @param namespaceName The name of the namespace (e.g., "translation", "common").
     * @param namespaceBuilder A lambda with receiver [TranslationConfiguration] to build the translations.
     */
    @Suppress("unused")
    fun ns(namespaceName: String, namespaceBuilder: TranslationConfiguration.() -> Unit) {
        namespaces[namespaceName] = TranslationConfiguration().also(namespaceBuilder)
    }

    /**
     * Converts the language configuration into a dynamic object suitable for `i18next` initialization.
     *
     * @return A dynamic object representing the namespaces and their translations.
     */
    internal fun getAsDynamic(): dynamic {
        val obj: dynamic = jso()
        namespaces.forEach { (key, translation) ->
            obj[key] = translation.getAsDynamic()
        }
        return obj
    }
}
