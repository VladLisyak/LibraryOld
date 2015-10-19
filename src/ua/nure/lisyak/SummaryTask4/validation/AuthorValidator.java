package ua.nure.lisyak.SummaryTask4.validation;

import ua.nure.lisyak.SummaryTask4.entity.Author;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Interpreter;

/**
 * Validates {@link Author} objects.
 */
public class AuthorValidator extends AbstractValidator {

    /**
     * Instantiates a new validator that validates {@link Author}.
     *
     * @param author     {@link Author} to validate
     * @param translator translator object for messages
     * @param locale     current locale value
     * @param locales    array of locales
     */
    public AuthorValidator(Author author, Interpreter translator, String locale, String[] locales) {
        super(translator, locale);
        if (author == null) {
            return;
        }
        
        validateName(author.getName(), locales);
        validateDescription(author.getDescription(), locales);
    }

    private void validateName(Interpretation name, String[] locales) {
        for (String locale : locales) {
            putIssue("name_" + locale, validateName(getTranslationValue(name, locale)));
        }
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (name.length() < 3 || name.length() > 100) {
            return Constants.Validation.LEN_3_TO_100;
        }
        return null;
    }

    private void validateDescription(Interpretation description, String[] locales) {
        for (String locale : locales) {
            putIssue("description_" + locale, validateDescription(getTranslationValue(description, locale)));
        }
    }

    private String validateDescription(String description) {
        if (description == null) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (description.isEmpty()) {
            return null;
        }
        if (description.length() < 5 || description.length() > 1000) {
            return Constants.Validation.LEN_5_TO_1000;
        }
        return null;
    }

}
