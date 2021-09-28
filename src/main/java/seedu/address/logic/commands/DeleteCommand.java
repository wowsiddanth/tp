package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a student identified by index or an event by name.\n"
            + "Parameters: s/[Student index] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT + "1\n"
            + "Parameters: e/[Event name] (must not be empty)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_EVENT + "Orientation";
}
