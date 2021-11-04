package nustracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class CommandResultTest {

    @Test
    public void constructor_toggleStudentsAndToggleEventsBothTrue_throwsAssertionError() {
        try {
            new CommandResult("feedback", false, false, false, false, false,
                    true, true);
        } catch (AssertionError e) {
            assertTrue(true);
        }
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false, false,
                false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, false, false,
                false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, false, false,
                false, false)));

        // different toggleStudents value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, false,
                true, false)));

        // different toggleEvents value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, false,
                false, true)));

        // different showSettings value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, false, false,
                false, false)));

        // different toggleTheme value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true, false,
                false, false)));

        // different toggleRefresh value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, true,
                false, false)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false, false, false, false,
                false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true, false, false, false,
                false, false).hashCode());

        // different toggleStudents value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, false, false,
                true, false).hashCode());

        // different toggleEvents value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, false, false,
                false, true).hashCode());

        // different showSettings value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false,
                true, false, false, false, false).hashCode());

        // different toggleTheme value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, true, false,
                false, false).hashCode());

        // different toggleRefresh value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false, false, true,
                false, false).hashCode());
    }
}
