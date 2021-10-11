package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_STUDENT_NUSNETID;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENT;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.student.NusNetId;
import nustracker.model.student.Student;

/**
 * Deletes a student identified by his/her NUS NetId.
 */
public class DeleteStudentCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = DeleteCommand.COMMAND_WORD
            + ": Deletes a student identified by NUS NetId.\n"
            + "Parameters: " + DeleteCommand.COMMAND_WORD + "[Student NUS NetId]\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + PREFIX_STUDENT + "e1234567";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final NusNetId nusNetId;

    public DeleteStudentCommand(NusNetId nusNetId) {
        this.nusNetId = nusNetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Student toDelete = model.getStudent(nusNetId);

        if (toDelete == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENT_NUSNETID, nusNetId));
        }

        model.deleteStudent(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && nusNetId.equals(((DeleteStudentCommand) other).nusNetId)); // state check
    }
}
