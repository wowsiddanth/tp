package nustracker.logic.commands;

import static nustracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;
import nustracker.testutil.TypicalEvents;
import nustracker.testutil.TypicalStudents;



/**
 * Contains integration tests (interaction with the Model) for {@code FilterEventCommand}.
 */
public class FilterEventCommandTest {
    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

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

    //    @Test
    //    public void execute_oneEvent_multipleStudentsFound() {
    //        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
    //        EnrolledEventsContainsKeywordsPredicate predicate = preparePredicate(TypicalEvents.ORIENTATION.getName().eventName);
    //        FilterCommand command = new FilterEventCommand(predicate);
    //        expectedModel.updateFilteredStudentList(predicate);
    //        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(TypicalStudents.ALICE),
    //                model.getFilteredStudentList());
    //    }

    /**
     * Parses {@code userInput} into a {@code EnrolledEventsContainsKeywordsPredicate}.
     */
    private EnrolledEventsContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EnrolledEventsContainsKeywordsPredicate(userInput);
    }
}
