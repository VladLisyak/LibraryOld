package ua.nure.lisyak.SummaryTask4.validation;


import ua.nure.lisyak.SummaryTask4.entity.Publisher;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.Interpreter;
import ua.nure.lisyak.SummaryTask4.util.Constants.Validation;


/**
 * Performs {@link Publisher} object validation.
 */
public class PublisherValidator extends AbstractValidator {

    /**
     * Instantiates a validator that validates {@link Publisher}.
     * @param publisher {@link Publisher} that needs validation
     * @param interp interpreter object
     * @param locale current locale
     * @param locales array of all locales
     */
    public PublisherValidator(Publisher publisher, Interpreter interp, String locale, String[] locales) {
        super(interp, locale);
        if (publisher == null) {
            return;
        }
        validateTitle(publisher.getTitle(), locales);
    }

    private void validateTitle(Interpretation title, String[] locales) {
        for (String locale : locales) {
            putIssue("title_" + locale, validateTitle(getTranslationValue(title, locale)));
        }
    }

    private String validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Validation.CANT_BE_EMPTY;
        }
        if (title.length() < 3 || title.length() > 100) {
            return Validation.LEN_3_TO_100;
        }
        return null;
    }

}
