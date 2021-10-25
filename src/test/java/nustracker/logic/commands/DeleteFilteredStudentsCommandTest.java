package nustracker.logic.commands;

import static nustracker.commons.core.Messages.MESSAGE_STUDENT_LIST_NOT_SHOWN;
import static nustracker.logic.commands.CommandTestUtil.assertCommandFailureShownEventList;
import static nustracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;
import nustracker.model.student.Student;
import nustracker.testutil.TypicalStudents;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteFilteredStudentsCommand}.
 */
public class DeleteFilteredStudentsCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        model.updateFilteredStudentList(currStudent -> true);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredStudentList(currStudent -> true);

        ObservableList<Student> refList = expectedModel.getFilteredStudentList();
        ArrayList<Student> newList = new ArrayList<>();

        for (Student currStudent : refList) {
            newList.add(currStudent);
        }


        for (Student currStudent : newList) {
            expectedModel.deleteStudent(currStudent);
        }

        DeleteFilteredStudentsCommand deleteFilteredStudentsCommand = new DeleteFilteredStudentsCommand();

        assertCommandSuccess(deleteFilteredStudentsCommand, model,
                DeleteFilteredStudentsCommand.MESSAGE_DELETE_ALL_FILTERED_SUCCESS, expectedModel);

    }

    @Test
    public void execute_unfilteredListWhenEventsShown_throwsCommandException() {
        DeleteFilteredStudentsCommand cmd = new DeleteFilteredStudentsCommand();

        assertCommandFailureShownEventList(cmd, model, MESSAGE_STUDENT_LIST_NOT_SHOWN);
    }



    @Test
    public void equals() {
        DeleteFilteredStudentsCommand cmd1 = new DeleteFilteredStudentsCommand();
        DeleteFilteredStudentsCommand cmd2 = new DeleteFilteredStudentsCommand();

        // same object -> returns true
        assertTrue(cmd1.equals(cmd1));

        // same command different object -> returns true
        assertTrue(cmd1.equals(cmd2));

        // different types -> returns false
        assertFalse(cmd1.equals(1));

        // null -> returns false
        assertFalse(cmd1.equals(null));

    }








}
