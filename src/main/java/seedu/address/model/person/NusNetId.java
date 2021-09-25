package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class NusNetId {

    public static final String MESSAGE_CONSTRAINTS =
            "Id should only contains alphanumeric characters and space, and it should not be blank!";

    /*
     * The first character of the address must a lowercase 'e', in line with
     * the NUS policy for ids
     */
    public static final String VALIDATION_REGEX = "e[\\p{Nd}]*";

    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param nusNetId A valid NUS net id
     */
    public NusNetId(String nusNetId) {
        requireNonNull(nusNetId);
        checkArgument(isValidNusNetId(nusNetId), MESSAGE_CONSTRAINTS);
        value = nusNetId;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidNusNetId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NusNetId // instanceof handles nulls
                && value.equals(((NusNetId) other).value)); //Check internal attributes
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
