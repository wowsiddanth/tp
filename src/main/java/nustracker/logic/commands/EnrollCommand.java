package nustracker.logic.commands;

import static nustracker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import nustracker.commons.core.Messages;
import nustracker.commons.core.index.Index;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Student;

/**
 * Adds an existing person to an event.
 */
public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the person identified to an event "
            + "by the index number used in the last person listing. "
            + "Existing event will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "e/ [EVENT]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "e/ Orientation camp.";
    public static final String MESSAGE_ADD_EVENT_SUCCESS = "Added event to Student: %1$s";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Removed event from Student: %1$s";

    private final Index index;
    private final EnrolledEvents enrolledEvents;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param enrolledEvents the person is to be added to
     */
    public EnrollCommand(Index index, EnrolledEvents enrolledEvents) {
        requireAllNonNull(index, enrolledEvents);

        this.index = index;
        this.enrolledEvents = enrolledEvents;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getYear(), studentToEdit.getMajor(), studentToEdit.getNusNetId(),
                studentToEdit.getTags());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether
     * the event is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Student personToEdit) {
        String message = !enrolledEvents.value.isEmpty() ? MESSAGE_ADD_EVENT_SUCCESS : MESSAGE_DELETE_EVENT_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrollCommand)) {
            return false;
        }

        // state check
        EnrollCommand e = (EnrollCommand) other;
        return index.equals(e.index)
                && enrolledEvents.equals(e.enrolledEvents);
    }
}
