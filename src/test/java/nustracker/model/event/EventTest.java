package nustracker.model.event;

import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTDATE_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTTIME_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_PARTICIPANT_FINAL;
import static nustracker.testutil.Assert.assertThrows;
import static nustracker.testutil.TypicalEvents.FINAL;
import static nustracker.testutil.TypicalEvents.TEST;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.testutil.EventBuilder;

class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getParticipants().remove(0));
    }

    @Test
    public void isSameEvent() {
        // same object -> returns true
        Assertions.assertTrue(TEST.isSameEvent(TEST));

        // null -> returns false
        Assertions.assertFalse(TEST.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedTest = new EventBuilder(TEST).withDate(VALID_EVENTDATE_FINAL).withTime(VALID_EVENTTIME_FINAL)
                .withParticipants(VALID_PARTICIPANT_FINAL).build();
        Assertions.assertTrue(TEST.isSameEvent(editedTest));

        // different name, all other attributes same -> returns false
        editedTest = new EventBuilder(TEST).withName(VALID_EVENTNAME_FINAL).build();
        Assertions.assertFalse(TEST.isSameEvent(editedTest));

        // name differs in case, all other attributes same -> returns false
        Event editedFinal = new EventBuilder(FINAL).withName(VALID_EVENTNAME_FINAL.toLowerCase()).build();
        Assertions.assertFalse(FINAL.isSameEvent(editedFinal));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_EVENTNAME_FINAL + " ";
        editedFinal = new EventBuilder(FINAL).withName(nameWithTrailingSpaces).build();
        Assertions.assertFalse(FINAL.isSameEvent(editedFinal));


    }

    @Test
    public void equals() {
        // same values -> returns true
        Event testCopy = new EventBuilder(TEST).build();
        Assertions.assertTrue(TEST.equals(testCopy));

        // same object -> returns true
        Assertions.assertTrue(TEST.equals(TEST));

        // null -> returns false
        Assertions.assertFalse(TEST.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TEST.equals(5));

        // different event -> returns false
        Assertions.assertFalse(TEST.equals(FINAL));

        // different name -> returns false
        Event editedTest = new EventBuilder(TEST).withName(VALID_EVENTNAME_FINAL).build();
        Assertions.assertFalse(TEST.equals(editedTest));

        // different date -> returns false
        editedTest = new EventBuilder(TEST).withDate(VALID_EVENTDATE_FINAL).build();
        Assertions.assertFalse(TEST.equals(editedTest));

        // different time -> returns false
        editedTest = new EventBuilder(TEST).withTime(VALID_EVENTTIME_FINAL).build();
        Assertions.assertFalse(TEST.equals(editedTest));

        // different participants -> returns false
        editedTest = new EventBuilder(TEST).withParticipants(VALID_PARTICIPANT_FINAL).build();
        Assertions.assertFalse(TEST.equals(editedTest));

        // different blacklist -> returns false
        editedTest = new EventBuilder(TEST).withBlacklist(VALID_PARTICIPANT_FINAL).build();
        Assertions.assertFalse(TEST.equals(editedTest));
    }

}
