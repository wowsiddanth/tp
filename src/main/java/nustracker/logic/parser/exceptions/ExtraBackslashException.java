package nustracker.logic.parser.exceptions;

import nustracker.commons.exceptions.IllegalValueException;

/**
 * Represents an error where the values contains extraneous backslashes.
 */
public class ExtraBackslashException extends IllegalValueException {

    public ExtraBackslashException() {
        super("Invalid command format! Commands that have arguments cannot have extraneous"
                + " backslashes or prefixes. ");
    }

    public ExtraBackslashException(String message) {
        super(message);
    }

    public ExtraBackslashException(String message, Throwable cause) {
        super(message, cause);
    }

}
