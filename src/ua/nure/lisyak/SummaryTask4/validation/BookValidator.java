package ua.nure.lisyak.SummaryTask4.validation;

import java.util.Calendar;
import java.util.List;

import ua.nure.lisyak.SummaryTask4.entity.Book;
import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.Interpreter;
import ua.nure.lisyak.SummaryTask4.util.Constants.Validation;


/**
 * Performs {@link Book} object validation.
 */
public class BookValidator extends AbstractValidator {


    /**
     * Instantiates a validator that validates {@link Book}s objects.
     *
     * @param book        {@link Book} that needs validation
     * @param publisherId id of book's publisher
     * @param authorIds   ids of books's authors
     * @param interp      interpreter object
     * @param locale      current locale
     * @param locales     array of locales
     */
    public BookValidator(Book book, Integer publisherId, List<Integer> authorIds, Interpreter interp, String locale, String[] locales) {
        super(interp, locale);
        if (book == null) {
            return;
        }
        validateTitle(book.getTitle(), locales);
        validateDescription(book.getDescription(), locales);
        putIssue("amount", validateAmount(book.getAmount()));
        putIssue("pages", validatePages(book.getPages()));
        putIssue("year", validateYear(book.getYear()));
        putIssue("publisherId", validatePublisher(publisherId));
        putIssue("authors", validateAuthors(authorIds));
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

    private void validateDescription(Interpretation description, String[] locales) {
        for (String locale : locales) {
            putIssue("description_" + locale, validateDescription(getTranslationValue(description, locale)));
        }
    }

    private String validateDescription(String description) {
        if (description == null) {
            return Validation.CANT_BE_EMPTY;
        }
        if (description.isEmpty()) {
            return null;
        }
        if (description.length() < 5 || description.length() > 1000) {
            return Validation.LEN_5_TO_1000;
        }
        return null;
    }

    private String validateAmount(Integer amount) {
        if (amount == null) {
            return Validation.CANT_BE_EMPTY;
        }
        if (amount < 0) {
            return Validation.CANT_BE_NEGATIVE;
        }
        return null;
    }

    private String validatePages(Integer pages) {
        if (pages == null) {
            return Validation.CANT_BE_EMPTY;
        }
        if (pages < 1) {
            return Validation.PAGES_COUNT;
        }
        return null;
    }

    private String validateYear(Integer year) {
        if (year == null) {
            return Validation.CANT_BE_EMPTY;
        }
        if (year < 0) {
            return Validation.CANT_BE_NEGATIVE;
        }
        if (year > Calendar.getInstance().get(Calendar.YEAR)) {
            return Validation.YEAR;
        }
        return null;
    }

    private String validatePublisher(Integer publisherId) {
        if (publisherId == null) {
            return Validation.CANT_BE_EMPTY;
        }
        if (publisherId < 0) {
            return Validation.CANT_BE_NEGATIVE;
        }
        return null;
    }

    private String validateAuthors(List<Integer> authorsIds) {
        if (authorsIds == null || authorsIds.size() == 0) {
            return Validation.CANT_BE_EMPTY;
        }
        for (Integer i : authorsIds) {
            if (i == null || i < 1) {
                return Validation.CANT_BE_NEGATIVE;
            }
        }
        return null;
    }

}
