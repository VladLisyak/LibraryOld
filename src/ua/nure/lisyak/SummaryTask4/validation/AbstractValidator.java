package ua.nure.lisyak.SummaryTask4.validation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.lisyak.SummaryTask4.entity.Interpretation;
import ua.nure.lisyak.SummaryTask4.util.Interpreter;

/**
 * Class that provides basic functionality for all {@link Validator}s.
 */
public abstract class AbstractValidator implements Validator {
	private static final Logger LOGGER = Logger.getLogger(AbstractValidator.class);
	
    private Interpreter interp;
    private Map<String, String> errMessages;
    private String locale;

    protected AbstractValidator(Interpreter interp, String locale) {
        this.interp = interp;
        this.locale = locale;
        errMessages = new HashMap<>();
    }

    @Override
    public boolean hasErrors() {
        return errMessages.size() != 0;
    }

    @Override
    public Map<String, String> getMessages() {
        return errMessages;
    }

    @Override
    public void putIssue(String key, String message) {
        if (key == null || message == null) {
            return;
        }
        LOGGER.debug("Put message " + message);
        errMessages.put(key, interp.translate(message, locale));
    }

    protected String getTranslationValue(Interpretation interp, String locale) {
        return interp == null ? null : interp.value(locale);
    }

}
