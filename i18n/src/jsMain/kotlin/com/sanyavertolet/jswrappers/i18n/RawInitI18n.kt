/**
* File containing an old way of initialization of `i18next`
*/

package com.sanyavertolet.jswrappers.i18n

/**
 * Function that encapsulates `i18next` initialization.
 *
 * @param isDebug flag to set debug mode
 * @param interpolationEscapeValue interpolation.escapeValue value
 */
@Suppress("UNUSED_PARAMETER")
@Deprecated(
    "Old API",
    replaceWith = ReplaceWith(
        "I18n",
        "com.sanyavertolet.jswrappers.i18n.core.I18n",
    ),
)
fun rawInitI18n(isDebug: Boolean = false, interpolationEscapeValue: Boolean = false) {
    js("""
         var i18n = require("i18next");
         var reactI18n = require("react-i18next");
         var Backend = require("i18next-http-backend");
         
         i18n
             .use(reactI18n.initReactI18next)
             .use(Backend.default)
             .init({
                 load: 'languageOnly',
                 initImmediate: false,
                 partialBundledLanguages: true,
                 ns: [
                     'main'
                 ],
                 backend: {
                     loadPath: '/locales/{{lng}}/{{ns}}.json'
                 },
                 lng: "en",
                 fallbackLng: "en",
                 debug: isDebug,
                 interpolation: {
                     escapeValue: interpolationEscapeValue
                 },
             }); 
     """)
}
