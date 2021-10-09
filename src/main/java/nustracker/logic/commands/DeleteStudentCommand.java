package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.List;

import nustracker.commons.core.Messages;
import nustracker.commons.core.index.Index;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.student.Student;

/**
 * Deletes a student identified using it's displayed index from the address book.
 */
public class DeleteStudentCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = DeleteCommand.COMMAND_WORD
            + ": Deletes a student identified by index.\n"
            + "Parameters: " + DeleteCommand.COMMAND_WORD + "[Student index] (must be a positive integer)\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + PREFIX_STUDENT + "1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";

    private final Index targetIndex;

    public DeleteStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteStudent(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteStudentCommand) other).targetIndex)); // state check
    }
}
