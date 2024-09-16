package com.sanyavertolet.jswrappers.cookie

/**
 * Cookie attributes that can be passed in [Cookie.remove], [Cookie.set] or [Cookie.get] methods
 *
 * @see <a href=https://github.com/js-cookie/js-cookie>Documentation on GitHub</a>
 */
@Suppress("unused")
external interface CookieAttribute {
    /**
     * A [String] indicating the path where the cookie is visible.
     */
    var path: String?

    /**
     * A [String] indicating a valid domain where the cookie should be visible.
     * The cookie will also be visible to all subdomains.
     */
    var domain: String?

    /**
     * Define when the cookie will be removed.
     * Value must be an [Int] which will be interpreted as days from time of creation.
     *
     * If omitted, the cookie becomes a session cookie.
     */
    var expires: Int?

    /**
     * Either true or false, indicating if the cookie transmission requires a secure protocol (https).
     */
    var secure: Boolean?
}
