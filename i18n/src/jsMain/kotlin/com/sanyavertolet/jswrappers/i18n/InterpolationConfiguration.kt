package com.sanyavertolet.jswrappers.i18n

import com.sanyavertolet.jswrappers.i18n.resources.TranslationConfiguration
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.encodeToDynamic

/**
 * Configuration options for interpolation in `i18next`.
 *
 * This class allows you to configure how interpolation is handled in `i18next`,
 * including formatting, escaping, and nesting of variables.
 *
 * @see <a href="https://www.i18next.com/translation-function/interpolation">i18n interpolation docs</a>
 */
@Suppress("unused")
@Serializable
class InterpolationConfiguration internal constructor() {
    /**
     * Custom format function used to format interpolated values.
     *
     * This function receives parameters `(value: Any, format: String, lng: String, options: Any)`
     * and should return the formatted value.
     *
     * **Note:** This property is not serialized and must be set via the [format] function.
     */
    @Transient
    private var format: dynamic = undefined

    /**
     * The separator used to split format parameters in the interpolation.
     *
     * Defaults to `","`.
     *
     * Example usage in a translation string: `{{value, format}}`.
     */
    var formatSeparator: String = ","

    /**
     * Custom escape function used to escape interpolated values.
     *
     * This function receives the parameter `(value: Any)` and should return the escaped value.
     *
     * **Note:** This property is not serialized and must be set via the [escape] function.
     */
    @Transient
    private var escape: dynamic = undefined

    /**
     * Whether to escape interpolated values.
     *
     * If `true`, the values will be escaped to prevent XSS attacks.
     *
     * Defaults to `true`.
     */
    var escapeValue: Boolean = true

    /**
     * Whether to use the raw value (before formatting) for escaping.
     *
     * If `true`, the raw value will be escaped before any formatting is applied.
     *
     * Defaults to `false`.
     */
    var useRawValueToEscape: Boolean = false

    /**
     * The prefix for interpolation placeholders.
     *
     * Defaults to `"{{"`.
     */
    var prefix: String = "{{"

    /**
     * The suffix for interpolation placeholders.
     *
     * Defaults to `"}}"`.
     */
    var suffix: String = "}}"

    /**
     * The escaped version of the prefix for interpolation.
     *
     * Useful if the prefix conflicts with other templating languages.
     *
     * Defaults to `null`.
     */
    var prefixEscaped: String? = null

    /**
     * The escaped version of the suffix for interpolation.
     *
     * Useful if the suffix conflicts with other templating languages.
     *
     * Defaults to `null`.
     */
    var suffixEscaped: String? = null

    /**
     * The prefix indicating that a value should not be escaped.
     *
     * Defaults to `"-"`.
     */
    var unescapePrefix: String = "-"

    /**
     * The suffix indicating that a value should not be escaped.
     *
     * Defaults to `null`.
     */
    var unescapeSuffix: String? = null

    /**
     * The prefix used for nesting translations.
     *
     * Defaults to `"$t("`.
     */
    var nestingPrefix: String = "\$t("

    /**
     * The suffix used for nesting translations.
     *
     * Defaults to `")"`.
     */
    var nestingSuffix: String = ")"

    /**
     * The escaped version of the nesting prefix.
     *
     * Defaults to `null`.
     */
    var nestingPrefixEscaped: String? = null

    /**
     * The escaped version of the nesting suffix.
     *
     * Defaults to `null`.
     */
    var nestingSuffixEscaped: String? = null

    /**
     * The separator used for options in nested interpolations.
     *
     * Defaults to `","`.
     */
    var nestingOptionsSeparator: String = ","

    /**
     * Default variables to be used in all interpolations.
     *
     * These variables can be referenced in translations without needing to pass them each time.
     *
     * **Note:** This property is not serialized and must be set via [rawDefaultVariables] or [defaultVariables].
     */
    @Transient
    var defaultVariables: dynamic = undefined

    /**
     * The maximum number of replacements to prevent infinite loops during interpolation.
     *
     * Defaults to `1000`.
     */
    var maxReplaces: Int = 1000

    /**
     * Whether to skip interpolation if variables are missing.
     *
     * If `true`, interpolation will not replace placeholders if the corresponding variable is not provided.
     *
     * Defaults to `true`.
     */
    var skipOnVariables: Boolean = true

    /**
     * Sets a custom format function for interpolation.
     *
     * @param formatFunction The custom format function `(value: Any, format: String, lng: String, options: Any) -> Any`.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun format(formatFunction: dynamic) {
        format = formatFunction
    }

    /**
     * Sets a custom escape function for interpolation.
     *
     * @param escapeFunction The custom escape function `(value: Any) -> Any`.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun escape(escapeFunction: dynamic) {
        escape = escapeFunction
    }

    /**
     * Sets default variables for interpolation using a dynamic object.
     *
     * @param defaultVariables A dynamic object containing default variables.
     */
    fun rawDefaultVariables(defaultVariables: dynamic) {
        this.defaultVariables = defaultVariables
    }

    /**
     * Converts the interpolation configuration into a dynamic object suitable for `i18next` initialization.
     *
     * @return A dynamic object representing the interpolation configuration.
     */
    @OptIn(ExperimentalSerializationApi::class)
    internal fun getAsDynamic(): dynamic {
        val configurationAsDynamic = json.encodeToDynamic(this)
        if (format != undefined) {
            configurationAsDynamic["format"] = format
        }
        if (escape != undefined) {
            configurationAsDynamic["escape"] = escape
        }
        if (defaultVariables != undefined) {
            configurationAsDynamic["defaultVariables"] = defaultVariables
        }
        return configurationAsDynamic
    }
}

/**
 * Sets default variables for interpolation using a [TranslationConfiguration].
 *
 * This function allows you to define default variables using the same DSL used for translations.
 *
 * @param translationConfiguration A lambda with receiver [TranslationConfiguration] to build the default variables.
 */
fun InterpolationConfiguration.defaultVariables(translationConfiguration: TranslationConfiguration.() -> Unit) {
    rawDefaultVariables(TranslationConfiguration().apply(translationConfiguration).getAsDynamic())
}
