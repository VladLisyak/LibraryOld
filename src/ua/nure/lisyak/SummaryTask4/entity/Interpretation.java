package ua.nure.lisyak.SummaryTask4.entity;

import java.util.Arrays;
import java.util.List;

import ua.nure.lisyak.SummaryTask4.annotations.IsColumn;

/**
 * Class for Interpretation entity.
 */
public class Interpretation extends BasicEntity {

	private static final long serialVersionUID = -8410812584672909385L;

	@IsColumn
    private String en;

    @IsColumn
    private String ru;

    /**
     * Instantiates an empty Interpretation object.
     */
    public Interpretation() {

    }

    /**
     * Creates a new Interpretation objects with specified values.
     *
     * @param enValue English value
     * @param ruValue Russian value
     */
    public Interpretation(String enValue, String ruValue) {
    	this.ru = ruValue;
        this.en = enValue;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String enValue) {
        this.en = enValue;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ruValue) {
        this.ru = ruValue;
    }

    /**
     * Interprets the value into specified language depending on current {@code locale}.
     *
     * @param locale current locale code
     * @return interpretation of value depending on current {@code locale}
     */
    public String value(String locale) {
        if (locale == null) {
            return en;
        }
        return locale.equals("en") ? en : ru;
    }

    /**
     * Gets value of instance on all languages.
     *
     * @return all values of the translation
     */
    public List<String> values() {
        return Arrays.asList(en, ru);
    }

    /**
     * Clones all values of the {@code location}.
     *
     * @param instance of Interpretation whose values to be cloned
     */
    public void clone(Interpretation translation) {
        en = translation.en;
        ru = translation.ru;
    }
}
