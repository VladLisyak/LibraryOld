package ua.nure.lisyak.SummaryTask4.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
/**
 * Contains values from specified bundle in all accessible locales.
 */
public class Interpreter {
	private static final Logger LOGGER = Logger.getLogger(Interpreter.class);
	private Map<String, ResourceBundle> resBundles;

    /**
     * Creates a new interpreter.
     *
     * @param bundlePath path to bundle that contains translations to be interpreted.
     * @param locales  array of locales
     */
    public Interpreter(String bundlePath, String[] locales) {
        resBundles = new HashMap<>();
        for (String locale : locales) {
            ResourceBundle resBundle = ResourceBundle.getBundle(bundlePath, new Locale(locale));
            LOGGER.debug("Bundle for path"+ bundlePath + " "+ locale);
            resBundles.put(locale, resBundle);
        }
    }

    /**
     * Gets an interpretation for the specified {@code key} on specified locale.
     *
     * @param key    key find
     * @param locale current locale
     * @return found interpretation
     */
    public String translate(String key, String locale) {
    	LOGGER.debug(key + " translation " +locale);
        ResourceBundle bundle = resBundles.get(locale);
        LOGGER.debug((bundle!=null) ? bundle.getLocale(): "bundle for " +locale+ " is empty");
        
        if (bundle == null) {
            return null;
        }
        
        return bundle.getString(key);
    }
}
