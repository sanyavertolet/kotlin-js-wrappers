package io.github.sanyavertolet.jswrappers.i18next.plugins

/**
 * Represents the `backend` configuration for `i18next`.
 *
 * This class is abstract so when a `backend` plugin wrapper is being implemented,
 * this class should be extended to be used as configuration for the plugin.
 */
@Suppress("unused")
abstract class BackendConfiguration {
    /**
     * Serializes the configuration into a `dynamic` object suitable for passing to JavaScript.
     *
     * @return A dynamic object representing the serialized configuration.
     */
    abstract fun getAsDynamic(): dynamic
}
