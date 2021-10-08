package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;
import static nustracker.logic.parser.CliSyntax.PREFIX_DATE;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_TIME;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.event.Event;

/**
 * Creates a new Event.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates an Event. "
            + "Students can now be enrolled into this event.\n"
            + "Parameters: "
            + PREFIX_NAME + "Event Name"
            + PREFIX_DATE + "Event Date (YYYY-MM-DD)"
            + PREFIX_TIME + "Event Time (24 hr time)"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Orientation"
            + PREFIX_DATE + "2022-01-01"
            + PREFIX_TIME + "0900";

    public static final String MESSAGE_SUCCESS = "Created event: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";

    private final Event event;

    /**
     * Creates an CreateCommand to add the specified {@code event}
     */
    public CreateCommand(Event event) {
        requireAllNonNull(event);
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(event)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(event);
        return new CommandResult(String.format(MESSAGE_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && event.equals(((CreateCommand) other).event));
    }
}
