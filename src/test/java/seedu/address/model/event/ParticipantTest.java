package seedu.address.model.event;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Participant(null));
    }

    @Test
    public void constructor_invalidParticipantName_throwsIllegalArgumentException() {
        String invalidParticipantName = "";
        assertThrows(IllegalArgumentException.class, () -> new Participant(invalidParticipantName));
    }

    @Test
    public void isValidParticipantName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Participant.isValidName(null));
    }

}
