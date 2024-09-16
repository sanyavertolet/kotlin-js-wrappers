package com.sanyavertolet.jswrappers.i18n.resources

import js.core.jso

/**
 * Represents the configuration for translations within a namespace or group.
 *
 * This class allows you to define individual translation key-value pairs
 * and to group translations hierarchically using nested scopes.
 */
class TranslationConfiguration internal constructor() {

    /**
     * Internal map holding direct translation key-value pairs.
     */
    private val translations: MutableMap<String, String> = mutableMapOf()

    /**
     * Internal map holding nested translation groups.
     *
     * Each key corresponds to a group name, and the value is another
     * [TranslationConfiguration] representing the nested translations.
     */
    private val inners: MutableMap<String, TranslationConfiguration> = mutableMapOf()

    /**
     * Creates a nested group of translations.
     *
     * This function allows you to create hierarchical translation keys by grouping related translations.
     *
     * @param key The key for the group.
     * @param innerScope A lambda with receiver [TranslationConfiguration] to define the nested translations.
     */
    fun group(key: String, innerScope: TranslationConfiguration.() -> Unit) {
        inners[key] = TranslationConfiguration().also(innerScope)
    }

    /**
     * Adds a translation key-value pair to the current scope.
     *
     * @param key The translation key.
     * @param value The translated string.
     */
    fun translation(key: String, value: String) {
        translations[key] = value
    }

    /**
     * Converts the translation configuration into a dynamic object.
     *
     * The dynamic object represents the translations in a format suitable for `i18next` initialization,
     * preserving the hierarchical structure created by groups.
     *
     * @return A dynamic object representing the translations and nested groups.
     */
    internal fun getAsDynamic(): dynamic {
        val obj: dynamic = jso()
        translations.forEach { (key, value) -> obj[key] = value }
        inners.forEach { (key, value) -> obj[key] = value.getAsDynamic() }
        return obj
    }
}
