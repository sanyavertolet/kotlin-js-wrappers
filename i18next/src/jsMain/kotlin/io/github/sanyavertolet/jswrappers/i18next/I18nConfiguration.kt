package io.github.sanyavertolet.jswrappers.i18next

import io.github.sanyavertolet.jswrappers.i18next.plugins.BackendConfiguration
import io.github.sanyavertolet.jswrappers.i18next.plugins.CacheConfiguration
import io.github.sanyavertolet.jswrappers.i18next.plugins.DetectionConfiguration
import io.github.sanyavertolet.jswrappers.i18next.resources.Resources
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.encodeToDynamic
import kotlin.reflect.createInstance

/**
 * Configuration class for `i18next` initialization.
 *
 * This class encapsulates all the configuration options available
 * for initializing `i18next` in a Kotlin/JS environment.
 *
 * It uses `kotlinx-serialization` to serialize the configuration into a
 * dynamic object that can be passed to the JavaScript `init` function.
 *
 * @see <a href="https://www.i18next.com/overview/configuration-options">i18n configuration docs</a>
 */
@Suppress("unused")
@Serializable
class I18nConfiguration internal constructor() {
    /**
     * Enables debug mode if set to `true`.
     *
     * Defaults to `false`.
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
     *
     * Defaults to `null`.
     */
    @SerialName("lng")
    var language: String? = null

    /**
     * Appends the namespace to the key when in CIMode.
     *
     * Defaults to `null`.
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
     *
     * Defaults to `null`.
     */
    @SerialName("supportedLngs")
    var supportedLanguages: List<String>? = null

    /**
     * If `true`, non-explicit supported languages are allowed.
     *
     * Defaults to `false`.
     */
    var nonExplicitSupportedLanguages: Boolean = false

    /**
     * Strategy for loading resources.
     *
     * Defaults to `"all"`.
     */
    @SerialName("load")
    var loadStrategy: String = "all"

    /**
     * List of languages to preload.
     *
     * Defaults to `null`.
     */
    var preload: List<String>? = null

    /**
     * If `true`, language codes are converted to lower case.
     *
     * Defaults to `false`.
     */
    @SerialName("lowerCaseLng")
    var lowerCaseLanguageCode: Boolean = false

    /**
     * If `true`, cleans the language code.
     *
     * Defaults to `false`.
     */
    var cleanCode: Boolean = false

    /**
     * List of namespaces to load.
     *
     * Defaults to `["translation"]`.
     */
    @SerialName("ns")
    var namespaces: List<String> = listOf("translation")

    /**
     * Default namespace used if not specified in the translation function.
     *
     * Defaults to `"translation"`.
     */
    @SerialName("defaultNS")
    var defaultNamespace: String = "translation"

    /**
     * List of fallback namespaces.
     *
     * Defaults to `[]`.
     */
    @SerialName("fallbackNS")
    var fallbackNamespaces: List<String> = emptyList()

    /**
     * If `true`, partially bundles languages.
     *
     * Defaults to `false`.
     */
    var partialBundledLanguages: Boolean = false

    /**
     * If `true`, missing keys are sent to the backend.
     *
     * Defaults to `false`.
     */
    var saveMissing: Boolean = false

    /**
     * If `true`, updates missing keys.
     *
     * Defaults to `false`.
     */
    var updateMissing: Boolean = false

    /**
     * Specifies where to save missing keys.
     *
     * Defaults to `"fallback"`.
     */
    var saveMissingTo: String = "fallback"

    /**
     * If `true`, saves missing plurals.
     *
     * Defaults to `true`.
     */
    var saveMissingPlurals: Boolean = true

    /**
     * If `true`, appends the namespace to missing keys.
     *
     * Defaults to `false`.
     */
    var appendNamespaceToMissingKey: Boolean = false

    /**
     * If `true`, falls back to the key if no value is found and the key is missing.
     *
     * Defaults to `false`.
     */
    var missingKeyNoValueFallbackToKey: Boolean = false

    /**
     * List of post-processing steps.
     *
     * Defaults to `[]`.
     */
    var postProcess: List<String> = emptyList()

    /**
     * If `true`, returns `null` for missing translations.
     *
     * Defaults to `false`.
     */
    var returnNull: Boolean = false

    /**
     * If `true`, returns an empty string for missing translations.
     *
     * Defaults to `true`.
     */
    var returnEmptyString: Boolean = true

    /**
     * If `true`, returns objects instead of strings for missing translations.
     *
     * Defaults to `false`.
     */
    var returnObjects: Boolean = false

    /**
     * If `true`, returns detailed information for missing translations.
     *
     * Defaults to `false`.
     */
    var returnDetails: Boolean = false

    /**
     * Specifies how to join arrays in translations.
     *
     * Defaults to `null`.
     */
    var joinArrays: String? = null

    /**
     * If `true`, skips interpolation.
     *
     * Defaults to `false`.
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
    var backend: dynamic = undefined

    /**
     * Holds the cache configuration.
     * This is a `dynamic` field and is not serialized to `dynamic` with `kotlinx-serialization`.
     */
    @Transient
    var cache: dynamic = undefined

    /**
     * If `false`, initialization is delayed until resources are loaded.
     *
     * Defaults to `true`.
     */
    var initImmediate: Boolean = true

    /**
     * Separator used for keys.
     *
     * Defaults to `"."`.
     */
    var keySeparator: String = "."

    /**
     * Separator used for namespaces.
     *
     * Defaults to `":"`.
     */
    var namespaceSeparator: String = ":"

    /**
     * Separator used for plurals.
     *
     * Defaults to `"_"`.
     */
    var pluralSeparator: String = "_"

    /**
     * Separator used for contexts.
     *
     * Defaults to `"_"`.
     */
    var contextSeparator: String = "_"

    /**
     * If `true`, ignores the JSON structure when flattening translations.
     *
     * Defaults to `true`.
     */
    var ignoreJsonStructure: Boolean = true

    /**
     * Maximum number of parallel reads when loading resources.
     *
     * Defaults to `10`.
     */
    var maxParallelReads: Int = 10

    /**
     * Configures the `resources` (translations) using a dynamic object.
     *
     * **Warning: no type checks provided with [rawResources], use [resources] when possible.**
     *
     * @param resourcesObject A dynamic `resources` object.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    fun rawResources(resourcesObject: dynamic) {
        resources = resourcesObject
    }

    /**
     * Configures the `interpolation` options using a dynamic builder.
     *
     * **Warning: no type checks provided with [rawInterpolation], use [interpolation] when possible.**
     *
     * @param interpolationObject A lambda with a dynamic receiver to build the `interpolation` object.
     */
    fun rawInterpolation(interpolationObject: dynamic) {
        interpolation = interpolationObject
    }

    /**
     * Configures the `detection` options using a dynamic object.
     *
     * **Warning: no type checks provided with [rawDetection], use [detection] when possible.**
     *
     * @param detectionObject A dynamic `detection` object.
     */
    fun rawDetection(detectionObject: dynamic) {
        detection = detectionObject
    }

    /**
     * Configures the `backend` options using a dynamic object.
     *
     * **Warning: no type checks provided with [rawBackend], use [backend] when possible.**
     *
     * @param backendObject A dynamic `backend` object.
     */
    fun rawBackend(backendObject: dynamic) {
        backend = backendObject
    }

    /**
     * Configures the `cache` options using a dynamic object.
     *
     * **Warning: no type checks provided with [rawCache], use [cache] when possible.**
     *
     * @param cacheObject A dynamic `cache` object.
     */
    fun rawCache(cacheObject: dynamic) {
        cache = cacheObject
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

/**
 * Configures the `resources` (translations) using [Resources] builder.
 *
 * @param resourcesBuilder A lambda with a [Resources] receiver to build the `resources` object.
 */
fun I18nConfiguration.resources(resourcesBuilder: Resources.() -> Unit) {
    rawResources(Resources().apply(resourcesBuilder).getAsDynamic())
}

/**
 * Configures the `interpolation` using [InterpolationConfiguration] builder.
 *
 * @param interpolationBuilder A lambda with a [InterpolationConfiguration] receiver to build the `interpolation` object.
 */
fun I18nConfiguration.interpolation(interpolationBuilder: InterpolationConfiguration.() -> Unit) {
    rawInterpolation(InterpolationConfiguration().apply(interpolationBuilder).getAsDynamic())
}

/**
 * Configures the `backend` using [BackendConfiguration] builder.
 *
 * @param backendBuilder A lambda with a [BackendConfiguration] receiver to build the `backend` object.
 */
@OptIn(ExperimentalJsReflectionCreateInstance::class)
inline fun <reified T : BackendConfiguration> I18nConfiguration.backend(backendBuilder: T.() -> Unit) {
    rawBackend(T::class.createInstance().apply(backendBuilder).getAsDynamic())
}

/**
 * Configures the `detection` using [DetectionConfiguration] builder.
 *
 * @param detectionBuilder A lambda with a [DetectionConfiguration] receiver to build the `detection` object.
 */
@OptIn(ExperimentalJsReflectionCreateInstance::class)
inline fun <reified T : DetectionConfiguration> I18nConfiguration.detection(detectionBuilder: T.() -> Unit) {
    rawDetection(T::class.createInstance().apply(detectionBuilder).getAsDynamic())
}

/**
 * Configures the `cache` options using a dynamic builder.
 *
 * @param cacheBuilder A lambda with a dynamic receiver to build the cache `object`.
 */
@OptIn(ExperimentalJsReflectionCreateInstance::class)
inline fun <reified Cache : CacheConfiguration> I18nConfiguration.cache(cacheBuilder: Cache.() -> Unit) {
    rawCache(Cache::class.createInstance().apply(cacheBuilder).getAsDynamic())
}
