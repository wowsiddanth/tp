package nustracker.model.event;

import static java.util.Objects.requireNonNull;

import nustracker.model.student.StudentId;

/**
 * Represents an Event's participant. Participants are stored by their NUS NetId.
 */
public class Participant {

    public final String studentId;

    /**
     * Constructs a {@code Id}.
     *
     * @param studentId A valid NUS NetId.
     */
    public Participant(String studentId) {
        requireNonNull(studentId);
        this.studentId = studentId;
    }

    /**
     * Returns true if a given string is a valid participant.
     */
    public static boolean isValidParticipant(String test) {
        return StudentId.isValidStudentId(test);
    }

    /**
     * Gets the participant's Nus NetId.
     * @return the Nus NetId object.
     */
    public StudentId getStudentId() {
        return new StudentId(studentId);
    }

    @Override
    public String toString() {
        return studentId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && studentId.equals(((StudentId) other).value)); //Check internal attributes
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}
