@file:Suppress("HEADER_MISSING_IN_NON_SINGLE_CLASS_FILE")
@file:JsModule("react-i18next")
@file:JsNonModule

package io.github.sanyavertolet.jswrappers.reacti18next

/**
 * @param namespaces [Array] of namespaces to load
 * @return [Translation] instance
 */
@JsName("useTranslation")
external fun useTranslation(namespaces: Array<String> = definedExternally): Translation
