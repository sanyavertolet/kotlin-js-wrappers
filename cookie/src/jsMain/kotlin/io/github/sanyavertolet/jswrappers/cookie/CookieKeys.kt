package io.github.sanyavertolet.jswrappers.cookie

/**
 * Class that encapsulates the cookie information
 *
 * @property key [String] cookie name
 * @property expires amount of days before a cookie is considered to be expired, [DEFAULT_EXPIRES] by default
 */
sealed class CookieKeys(val key: String, val expires: Int = DEFAULT_EXPIRES) {
    /**
     * Cookie that indicates that user as accepted cookie policy
     */
    data object IsCookieOk : CookieKeys("isCookieOk")

    /**
     * Cookie that stores preferred platform language
     */
    data object PreferredLanguage : CookieKeys("language")
    companion object {
        /**
         * Default value for [CookieKeys.expires]
         */
        const val DEFAULT_EXPIRES = 365
    }
}
