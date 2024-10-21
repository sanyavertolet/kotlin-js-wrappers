package io.github.sanyavertolet.jswrappers.reacti18next

import io.github.sanyavertolet.jswrappers.i18next.I18n
import io.github.sanyavertolet.jswrappers.i18next.TranslationFunction
import io.github.sanyavertolet.jswrappers.i18next.TranslationFunctionWithReceiver

/**
 * Represents the return value of `useTranslation` hook
 * @see useTranslation
 */
@Suppress("NOTHING_TO_INLINE")
sealed class Translation {
    /**
     * Receives key and return a localized value.
     *
     * @param key translation key
     * @return localized value
     */
    inline fun translateLambda(key: String): String = asDynamic()[0].unsafeCast<TranslationFunction>()(key)

    /**
     * @return t-function that receives a key and returns a localized value
     */
    inline operator fun component1(): TranslationFunctionWithReceiver = { translateLambda(this) }

    /**
     * Get an [I18n] instance and use
     *
     * ```
     *   i18n.changeLanguage("<LANGUAGE>")
     * ```
     *
     * in order to change language
     *
     * @return an [I18n] instance
     */
    inline operator fun component2(): I18n = asDynamic()[1].unsafeCast<I18n>()

    /**
     * @return `ready` flag
     */
    @Suppress("FUNCTION_BOOLEAN_PREFIX")
    inline operator fun component3(): Boolean = asDynamic()[2].unsafeCast<Boolean>()

    /**
     * Operator that should be used in order to get rid of this:
     *
     * ```
     *   val (t) = useTranslation()
     * ```
     * and use this:
     *
     * ```
     *   val t = useTranslation()
     * ```
     *
     * @param key translation key
     * @return localized value by [key]
     * @see component1
     */
    inline operator fun invoke(key: String): String = translateLambda(key)
}

/**
 * @param namespace locale namespace
 *
 * @see useTranslation
 */
fun useTranslation(namespace: String) = useTranslation(arrayOf(namespace))