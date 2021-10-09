package nustracker.model.student;

import static java.util.Objects.requireNonNull;

/**
 * This class represent any event(s) that a person is a part of.
 */
public class EnrolledEvents {

    public static final String MESSAGE_CONSTRAINTS = "Events can take any values, and it should not be blank";

    public final String value;

    /**
     * Constructs an {@code EnrolledEvents}.
     *
     * @param event A valid event.
     */
    public EnrolledEvents(String event) {
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
                || (other instanceof EnrolledEvents // instanceof handles nulls
                && value.equals(((EnrolledEvents) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
