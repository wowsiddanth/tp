package nustracker.logic.commands;

import static nustracker.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;

/**
 * Contains methods for testing the event commands
 */
class EnrollCommandTest {

    private static final String EVENT_STUB = "Some event";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addEventUnfilteredList_success() {
//        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
//        Student editedStudent = new StudentBuilder(firstStudent).withEvent(EVENT_STUB).build();
//
//        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_STUDENT,
//                new EnrolledEvents(editedStudent.getEvents().value));
//
//        String expectedMessage = String.format(EnrollCommand.MESSAGE_ADD_EVENT_SUCCESS, editedStudent);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setStudent(firstStudent, editedStudent);
//
//        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteEventUnfilteredList_success() {
//        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
//        Student editedStudent = new StudentBuilder(firstStudent).withEvent("").build();
//
//        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_STUDENT,
//                new EnrolledEvents(editedStudent.getEvents().toString()));
//
//        String expectedMessage = String.format(EnrollCommand.MESSAGE_DELETE_EVENT_SUCCESS, editedStudent);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setStudent(firstStudent, editedStudent);
//
//        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
//        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
//
//        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
//        Student editedStudent = new StudentBuilder(model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()))
//                .withEvent(EVENT_STUB).build();
//
//        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_STUDENT,
//                new EnrolledEvents(editedStudent.getEvents().value));
//
//        String expectedMessage = String.format(EnrollCommand.MESSAGE_ADD_EVENT_SUCCESS, editedStudent);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setStudent(firstStudent, editedStudent);
//
//        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
//        EnrollCommand enrollCommand = new EnrollCommand(outOfBoundIndex, new EnrolledEvents(VALID_EVENT_BOB));
//
//        assertCommandFailure(enrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
//        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
//        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());
//
//        EnrollCommand enrollCommand = new EnrollCommand(outOfBoundIndex, new EnrolledEvents(VALID_EVENT_BOB));
//
//        assertCommandFailure(enrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }
}
