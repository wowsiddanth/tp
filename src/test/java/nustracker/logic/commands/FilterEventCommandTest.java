package nustracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;



/**
 * Contains integration tests (interaction with the Model) for {@code FilterEventCommand}.
 */
public class FilterEventCommandTest {

    @Test
    public void equals() {
        EnrolledEventsContainsKeywordsPredicate firstPredicate =
                new EnrolledEventsContainsKeywordsPredicate("first");
        EnrolledEventsContainsKeywordsPredicate secondPredicate =
                new EnrolledEventsContainsKeywordsPredicate("second");

        FilterCommand filterFirstCommand = new FilterEventCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterEventCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different events -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }
}
