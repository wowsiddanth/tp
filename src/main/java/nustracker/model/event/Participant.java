package nustracker.model.event;

import static java.util.Objects.requireNonNull;

import nustracker.model.student.NusNetId;

/**
 * Represents an Event's participant. Participants are stored by their NUS NetId.
 */
public class Participant {

    public final String nusNetId;

    /**
     * Constructs a {@code Id}.
     *
     * @param nusNetId A valid NUS NetId.
     */
    public Participant(String nusNetId) {
        requireNonNull(nusNetId);
        this.nusNetId = nusNetId;
    }

    /**
     * Returns true if a given string is a valid participant.
     */
    public static boolean isValidParticipant(String test) {
        return NusNetId.isValidNusNetId(test);
    }

    /**
     * Gets the participant's Nus NetId.
     * @return the Nus NetId object.
     */
    public NusNetId getNusNetId() {
        return new NusNetId(nusNetId);
    }

    @Override
    public String toString() {
        return nusNetId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NusNetId // instanceof handles nulls
                && nusNetId.equals(((NusNetId) other).value)); //Check internal attributes
    }

    @Override
    public int hashCode() {
        return nusNetId.hashCode();
    }
}
