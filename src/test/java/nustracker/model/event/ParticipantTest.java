package nustracker.model.event;

import static nustracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Participant(null));
    }

    @Test
    public void isValidParticipantName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Participant.isValidParticipant(null));
    }

}
