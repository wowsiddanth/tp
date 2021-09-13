package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * This class represents an event that a person is a part of.
 */
public class Event {

    public static final String MESSAGE_CONSTRAINTS = "Events can take any values, and it should not be blank";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param event A valid address.
     */
    public Event(String event) {
        requireNonNull(event);
        value = event;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Event // instanceof handles nulls
                && value.equals(((Event) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
