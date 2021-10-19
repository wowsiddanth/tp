package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.transformation.FilteredList;
import nustracker.model.Model;
import nustracker.model.student.Student;

/**
 * Exports the emails of the students in the current displayed list.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS = "Emails exported";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        FilteredList<Student> filteredStudents = model.getFilteredStudentList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
