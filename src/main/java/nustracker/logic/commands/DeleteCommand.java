package nustracker.logic.commands;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;

/**
 * A command used to delete a student or an event.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by his/her Student ID "
            + "or an event by name.\n"
            + "Parameters: " + PREFIX_STUDENTID + "[Student ID]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENTID + "1\n"
            + "Parameters:" + PREFIX_EVENT + "[Event name]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT + "Orientation";
}
