package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import javafx.collections.ObservableList;
import nustracker.commons.util.FileUtil;
import nustracker.logic.commands.exceptions.CommandException;
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
        ObservableList<Student> filteredStudents = model.getFilteredStudentList();
        System.out.println(filteredStudents);
        filteredStudents.stream().map(Student::getEmail);

        try {
            FileUtil.createIfMissing(Path.of("data\\Exported.txt") );
        } catch (IOException ioe) {
            throw new CommandException("Could not export data properly" + ioe, ioe);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
