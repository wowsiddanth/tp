package nustracker.logic.commands;

//import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_TEST;
//import static nustracker.logic.commands.CommandTestUtil.assertCommandFailure;
//import static nustracker.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static nustracker.logic.commands.CommandTestUtil.showStudentAtIndex;
//import static nustracker.testutil.TypicalEvents.MATH_OLYMPIAD;
//import static nustracker.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
//import static nustracker.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import nustracker.model.event.Event;
//import nustracker.testutil.EventBuilder;

//import org.junit.jupiter.api.Test;
//
//import nustracker.commons.core.Messages;
//import nustracker.commons.core.index.Index;
//import nustracker.model.AddressBook;

import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;
//import nustracker.model.student.EnrolledEvents;
//import nustracker.model.student.Student;
//import nustracker.testutil.StudentBuilder;
import nustracker.testutil.TypicalStudents;

/**
 * Contains methods for testing the event commands
 */
class EnrollCommandTest {

    private static final String EVENT_STUB = "Some event";

    private Model model = new ModelManager(TypicalStudents.getTypicalAddressBook(), new UserPrefs());

    //    @Test
    //    void execute_addEventUnfilteredList_success() {
    //        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
    //
    //        Event mathOlympiad = model.getEvent(MATH_OLYMPIAD.getName());
    //
    //        Student enrolledStudent = new StudentBuilder(firstStudent).withEvent(mathOlympiad).build();
    //
    //        Event updatedMathOlympiad = new EventBuilder(mathOlympiad)
    //                .addParticipant(firstStudent.getNusNetId().getNusNetIdString()).build();
    //
    //        EnrollCommand enrollCommand = new EnrollCommand(firstStudent.getNusNetId(),
    //                MATH_OLYMPIAD.getName());
    //
    //        String expectedMessage = String.format(EnrollCommand.MESSAGE_ADD_TO_EVENT_SUCCESS,
    //                enrolledStudent.getName().toString(),
    //                enrolledStudent.getNusNetId().getNusNetIdString(),
    //                mathOlympiad.getName().getEventName());
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setStudent(firstStudent, enrolledStudent);
    //        expectedModel.setEvent(mathOlympiad, updatedMathOlympiad);
    //
    //        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_deleteEventUnfilteredList_success() {
    //        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
    //        Student editedStudent = new StudentBuilder(firstStudent).withEvent("To edit later").build();
    //
    //        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_STUDENT,
    //                new EnrolledEvents(editedStudent.getEvents().toString()));
    //
    //        String expectedMessage = String.format(EnrollCommand.MESSAGE_DELETE_EVENT_SUCCESS, editedStudent);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setStudent(firstStudent, editedStudent);
    //
    //         assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_filteredList_success() {
    //        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
    //
    //        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
    //        Student editedStudent = new StudentBuilder(model.getFilteredStudentList()
    //                .get(INDEX_FIRST_STUDENT.getZeroBased()))
    //                .withEvent(EVENT_STUB).build();
    //
    //        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_STUDENT,
    //                new EnrolledEvents(editedStudent.getEvents().value));
    //
    //        String expectedMessage = String.format(EnrollCommand.MESSAGE_ADD_TO_EVENT_SUCCESS, editedStudent);
    //
    //        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    //        expectedModel.setStudent(firstStudent, editedStudent);
    //
    //        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_invalidStudentIndexUnfilteredList_failure() {
    //        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
    //        EnrollCommand enrollCommand = new EnrollCommand(outOfBoundIndex,
    //                  new EnrolledEvents(VALID_EVENTNAME_TEST));
    //
    //        assertCommandFailure(enrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    //    }
    //
    //    @Test
    //    public void execute_invalidStudentIndexFilteredList_failure() {
    //        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
    //        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());
    //
    //        EnrollCommand enrollCommand = new EnrollCommand(outOfBoundIndex,
    //                  new EnrolledEvents(VALID_EVENTNAME_TEST));
    //
    //        assertCommandFailure(enrollCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    //    }
}
