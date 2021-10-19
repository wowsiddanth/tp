package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Student> filteredStudents = model.getFilteredStudentList();
//        System.out.println(Arrays.toString(filteredStudents.stream().map(Student::getEmail).toArray()));

        try {
            Path path = Path.of("data\\Exported.txt");
            FileUtil.createIfMissing(path);
            // Overwrites File all the time
            FileUtil.writeToFile(path, Arrays.toString(filteredStudents.stream().map(Student::getEmail).toArray()));
        } catch (IOException ioe) {
            throw new CommandException("Could not export data properly" + ioe, ioe);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
