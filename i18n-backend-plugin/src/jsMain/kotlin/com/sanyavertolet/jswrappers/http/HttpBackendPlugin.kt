/**
 * File containing a new way of initialization of `i18next-http-backend`
 */

package com.sanyavertolet.jswrappers.http

import com.sanyavertolet.jswrappers.i18n.I18n

private val httpBackendPlugin: dynamic = kotlinext.js.require("i18next-http-backend")

/**
 * Uses a `i18next-http-backend` plugin with the `i18next` instance.
 *
 * @return The [I18n] instance for chaining.
 */
fun I18n.useHttpBackendPlugin(): I18n = use(httpBackendPlugin.default)
