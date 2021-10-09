package nustracker.logic.commands;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENT;

/**
 * Deletes a student identified using it's displayed index from the address book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list "
            + "or an event by name.\n"
            + "Parameters: s/[Student index] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT + "1\n"
            + "Parameters: e/[Event name] (must not be empty)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT + "Orientation";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";
}
