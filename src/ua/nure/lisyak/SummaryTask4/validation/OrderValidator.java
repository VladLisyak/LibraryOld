package ua.nure.lisyak.SummaryTask4.validation;

import ua.nure.lisyak.SummaryTask4.util.Constants;
import ua.nure.lisyak.SummaryTask4.util.Interpreter;


/**
 * Validates {@link Order} properties.
 */
public class OrderValidator extends AbstractValidator {

    /**
     * Instantiates a validator that validates orders days count.
     *
     * @param days   		    orders' days count
     * @param interp	interpreter object
     * @param locale     		current locale
     */
    public OrderValidator(Integer days, Interpreter interp, String locale) {
        super(interp, locale);
        putIssue("days", validateDays(days));
    }

    /**
     * Instantiates a validator that validates {@link Book#id} and subscription number
     *
     * @param bookId         id of the book
     * @param subscriptionId subscription number
     * @param interp     	 interpreter object
     * @param locale         current locale
     */
    public OrderValidator(Integer bookId, Integer subscriptionId, Interpreter interp, String locale) {
        super(interp, locale);
        putIssue("bookId", validateId(bookId));
        putIssue("subscriptionId", validateId(subscriptionId));
    }

    private String validateId(Integer id) {
        if (id == null) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (id < 1) {
            return Constants.Validation.CANT_BE_NEGATIVE;
        }
        return null;
    }

    private String validateDays(Integer days) {
        if (days == null) {
            return Constants.Validation.CANT_BE_EMPTY;
        }
        if (days < 1) {
            return Constants.Validation.CANT_BE_NEGATIVE;
        }
        if (days > Constants.Settings.MAX_CHECK_OUT_PERIOD) {
            return Constants.Validation.TOO_BIG;
        }
        return null;
    }

}

