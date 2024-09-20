/**
 * File containing a new way of initialization of `react-i18next`
 */

package io.github.sanyavertolet.jswrappers.reacti18next

import io.github.sanyavertolet.jswrappers.i18next.I18n

/**
 * Dynamically imports the `react-i18next` module.
 *
 * This private dynamic variable holds the `react-i18next` module required at runtime.
 */
private val reactI18next: dynamic = kotlinext.js.require("react-i18next")

/**
 * Integrates the `i18next` instance with `React` using `react-i18next`.
 *
 * This extension function allows the `i18next` instance to use the `React` integration provided by `react-i18next`.
 * It enables `React` components to utilize translation functionalities through hooks and higher-order components.
 *
 * @receiver The [I18n] instance to which the `React` integration will be added.
 * @return The updated [I18n] instance with `React` integration.
 */
@Suppress("unused")
fun I18n.useReactI18next() = use(reactI18next.initReactI18next)
