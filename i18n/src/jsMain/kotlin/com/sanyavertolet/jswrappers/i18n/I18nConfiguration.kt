package com.sanyavertolet.jswrappers.i18n

import com.sanyavertolet.jswrappers.i18n.resources.Resources
import js.core.jso
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.encodeToDynamic

/**
 * Configuration class for i18next initialization.
 *
 * This class encapsulates all the configuration options available
 * for initializing i18next in a Kotlin/JS environment.
 *
 * It uses `kotlinx-serialization` to serialize the configuration into a
 * dynamic object that can be passed to the JavaScript `init` function.
 *
 * @see <a href="https://www.i18next.com/overview/configuration-options">i18n configuration docs</a>
 */
@Suppress("unused")
@Serializable
class I18nConfiguration {

    /**
     * Enables debug mode if set to `true`.
     */
    var debug: Boolean = false

    /**
     * Holds the resources (translations) for different languages and namespaces.
     * This is a `dynamic` field and is not serialized to `dynamic` with `kotlinx-serialization`.
     */
    @Transient
    private var resources: dynamic = undefined

    /**
     * Specifies the initial language to load.
     */
    @SerialName("lng")
    var language: String? = null

    /**
     * Appends the namespace to the key when in CIMode.
     */
    var appendNamespaceToCIMode: String? = null

    /**
     * Fallback language if the specified language is not available.
     * Defaults to `"dev"`.
     */
    @SerialName("fallbackLng")
    var fallbackLanguage: String = "dev"

    /**
     * List of supported languages.
     */
    @SerialName("supportedLngs")
    var supportedLanguages: List<String>? = null

    /**
     * If `true`, non-explicit supported languages are allowed.
     */
    var nonExplicitSupportedLanguages: Boolean = false

    /**
     * Strategy for loading resources. Defaults to `"all"`.
     */
    @SerialName("load")
    var loadStrategy: String = "all"

    /**
     * List of languages to preload.
     */
    var preload: List<String>? = null

    /**
     * If `true`, language codes are converted to lower case.
     */
    @SerialName("lowerCaseLng")
    var lowerCaseLanguageCode: Boolean = false

    /**
     * If `true`, cleans the language code.
     */
    var cleanCode: Boolean = false

    /**
     * List of namespaces to load. Defaults to `["translation"]`.
     */
    @SerialName("ns")
    var namespaces: List<String> = listOf("translation")

    /**
     * Default namespace used if not specified in the translation function.
     * Defaults to `"translation"`.
     */
    @SerialName("defaultNS")
    var defaultNamespace: String = "translation"

    /**
     * List of fallback namespaces.
     */
    @SerialName("fallbackNS")
    var fallbackNamespaces: List<String> = emptyList()

    /**
     * If `true`, partially bundles languages.
     */
    var partialBundledLanguages: Boolean = false

    /**
     * If `true`, missing keys are sent to the backend.
     */
    var saveMissing: Boolean = false

    /**
     * If `true`, updates missing keys.
     */
    var updateMissing: Boolean = false

    /**
     * Specifies where to save missing keys. Defaults to `"fallback"`.
     */
    var saveMissingTo: String = "fallback"

    /**
     * If `true`, saves missing plurals.
     */
    var saveMissingPlurals: Boolean = true

    /**
     * If `true`, appends the namespace to missing keys.
     */
    var appendNamespaceToMissingKey: Boolean = false

    /**
     * If `true`, falls back to the key if no value is found and the key is missing.
     */
    var missingKeyNoValueFallbackToKey: Boolean = false

    /**
     * List of post-processing steps.
     */
    var postProcess: List<String> = emptyList()

    /**
     * If `true`, returns `null` for missing translations.
     */
    var returnNull: Boolean = false

    /**
     * If `true`, returns an empty string for missing translations.
     */
    var returnEmptyString: Boolean = true

    /**
     * If `true`, returns objects instead of strings for missing translations.
     */
    var returnObjects: Boolean = false

    /**
     * If `true`, returns detailed information for missing translations.
     */
    var returnDetails: Boolean = false

    /**
     * Specifies how to join arrays in translations.
     */
    var joinArrays: String? = null

    /**
     * If `true`, skips interpolation.
     */
    var skipInterpolation: Boolean = false

    /**
     * Holds the interpolation configuration.
     * This is a `dynamic` field and is not serialized to `dynamic` with `kotlinx-serialization`.
     */
    @Transient
    var interpolation: dynamic = undefined

    /**
     * Holds the detection configuration.
     * This is a `dynamic` field and is not serialized to `dynamic` with `kotlinx-serialization`.
     */
    @Transient
    var detection: dynamic = undefined

    /**
     * Holds the backend configuration.
     * This is a `dynamic` field and is not serialized to `dynamic` with `kotlinx-serialization`.
     */
    @Transient
    private var backend: dynamic = undefined

    /**
     * Holds the cache configuration.
     * This is a `dynamic` field and is not serialized to `dynamic` with `kotlinx-serialization`.
     */
    @Transient
    var cache: dynamic = undefined

    /**
     * If `false`, initialization is delayed until resources are loaded.
     */
    var initImmediate: Boolean = true

    /**
     * Separator used for keys. Defaults to `"."`.
     */
    var keySeparator: String = "."

    /**
     * Separator used for namespaces. Defaults to `":"`.
     */
    var namespaceSeparator: String = ":"

    /**
     * Separator used for plurals. Defaults to `"_"`.
     */
    var pluralSeparator: String = "_"

    /**
     * Separator used for contexts. Defaults to `"_"`.
     */
    var contextSeparator: String = "_"

    /**
     * If `true`, ignores the JSON structure when flattening translations.
     */
    var ignoreJsonStructure: Boolean = true

    /**
     * Maximum number of parallel reads when loading resources.
     * Defaults to `10`.
     */
    var maxParallelReads: Int = 10

    /**
     * Configures the `resources` (translations) using a dynamic object.
     *
     * **Warning: no type checks provided with [rawResources], use [resources] when possible.**
     *
     * @param res a dynamic object that configures resources
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun rawResources(res: dynamic) {
        resources = res
    }

    /**
     * Configures the `resources` (translations) using [Resources] builder.
     *
     * @param resourcesBuilder A lambda with a [Resources] receiver to build the `resources` object.
     */
    fun resources(resourcesBuilder: Resources.() -> Unit) {
        resources = Resources().apply(resourcesBuilder).getAsDynamic()
    }

    /**
     * Configures the `interpolation` options using a dynamic builder.
     *
     * @param interpolationBuilder A lambda with a dynamic receiver to build the `interpolation` object.
     */
    fun interpolation(interpolationBuilder: dynamic.() -> Unit) {
        interpolation = jso(interpolationBuilder)
    }

    /**
     * Configures the `detection` options using a dynamic builder.
     *
     * @param detectionBuilder A lambda with a dynamic receiver to build the `detection` object.
     */
    fun detection(detectionBuilder: dynamic.() -> Unit) {
        detection = jso(detectionBuilder)
    }

    /**
     * Configures the `backend` options using a dynamic builder.
     *
     * @param backendBuilder A lambda with a dynamic receiver to build the `backend` object.
     */
    fun backend(backendBuilder: dynamic.() -> Unit) {
        backend = jso(backendBuilder)
    }

    /**
     * Configures the `cache` options using a dynamic builder.
     *
     * @param cacheBuilder A lambda with a dynamic receiver to build the cache `object`.
     */
    fun cache(cacheBuilder: dynamic.() -> Unit) {
        cache = jso(cacheBuilder)
    }

    /**
     * Serializes the configuration into a `dynamic` object suitable for passing to JavaScript.
     *
     * @return A dynamic object representing the serialized configuration.
     */
    @OptIn(ExperimentalSerializationApi::class)
    fun getAsDynamic(): dynamic {
        val configurationAsDynamic = json.encodeToDynamic(this)
        if (resources != undefined) {
            configurationAsDynamic["resources"] = resources
        }
        if (backend != undefined) {
            configurationAsDynamic["backend"] = backend
        }
        if (cache != undefined) {
            configurationAsDynamic["cache"] = cache
        }
        if (interpolation != undefined) {
            configurationAsDynamic["interpolation"] = interpolation
        }
        if (detection != undefined) {
            configurationAsDynamic["detection"] = detection
        }

        console.log(configurationAsDynamic)

        return configurationAsDynamic
    }
}
