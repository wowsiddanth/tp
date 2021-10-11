package nustracker.model.student;

import static java.util.Objects.requireNonNull;

import nustracker.commons.util.AppUtil;

/**
 * Represents a Student's NUS NetID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNusNetId(String)}
 */
public class NusNetId {

    public static final String MESSAGE_CONSTRAINTS =
            "NUS NetId should start with 'e', should be followed by 7 digits, and it should not be blank!";

    /*
     * The first character of the address must a lowercase 'e', in line with
     * current NUS guidelines for ids, followed by 7 numerical digits
     */
    public static final String VALIDATION_REGEX = "e[\\p{Nd}]{7}";

    public final String value;

    /**
     * Constructs a {@code Id}.
     *
     * @param nusNetId A valid NUS NetId
     */
    public NusNetId(String nusNetId) {
        requireNonNull(nusNetId);
        AppUtil.checkArgument(isValidNusNetId(nusNetId), MESSAGE_CONSTRAINTS);
        value = nusNetId;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidNusNetId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Gets the Nus NetId in String form.
     * @return the Nus NetId in String form.
     */
    public String getNusNetIdString() {
        return value;
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
