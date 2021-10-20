package nustracker.logic.commands;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENT;

/**
 * A command used to delete a student or an event.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by his/her student ID "
            + "or an event by name.\n"
            + "Parameters: s/[Student ID]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT + "1\n"
            + "Parameters: e/[Event name]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT + "Orientation";
}
