package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.ui.MainWindow;

public class DeleteFilteredStudentsCommand extends Command {

    public static final String COMMAND_WORD = "delfiltered";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all the students that are currently shown in the student list. "
            + "Change which students are shown using the filter command.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_DELETE_ALL_FILTERED_SUCCESS =
            "Successfully deleted all shown students in the student list.";
    public static final String MESSAGE_STUDENT_LIST_NOT_SHOWN =
            "The Student list has to be shown before this command can be used.";





    @Override
    public CommandResult execute(Model model, MainWindow.CurrentlyShownList currentlyShownList)
            throws CommandException {
        requireNonNull(model);

        if (currentlyShownList != MainWindow.CurrentlyShownList.STUDENTS_LIST) {
            throw new CommandException(MESSAGE_STUDENT_LIST_NOT_SHOWN);
        }

        ObservableList<Student> filteredStudents = model.getFilteredStudentList();

        for (Student currStudent : filteredStudents) {
            StudentId currStudentId = currStudent.getStudentId();
            DeleteStudentCommand delThisStudent = new DeleteStudentCommand(currStudentId);

            delThisStudent.execute(model);

        }


        return new CommandResult(MESSAGE_DELETE_ALL_FILTERED_SUCCESS);
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteFilteredStudentsCommand)) {
            return false;
        } else {
            // All DeleteFilteredCommands are the same.
            return true;
        }

    }
}
