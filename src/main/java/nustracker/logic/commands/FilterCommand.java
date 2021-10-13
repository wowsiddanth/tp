package nustracker.logic.commands;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_NUSNETID;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;

/**
 * Filters and lists all students whose data contain a specific field.
 */
public abstract class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all students by name, NUS NetId, or Events"
            + "by the specified keywords (case-insensitive).\n"
            + "Parameters: " + PREFIX_NAME + "STUDENT_NAME [MORE_STUDENT_NAMES]... \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie\n"
            + "Parameters: " + PREFIX_NUSNETID + "STUDENT_ID [MORE_STUDENT_IDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NUSNETID + "e1234567 e234567 e3456789\n"
            + "Parameters: " + PREFIX_EVENT + "EVENT_NAME\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT + "Orientation\n";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
