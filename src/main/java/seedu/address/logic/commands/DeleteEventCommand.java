package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

/**
 * Deletes an event identified by its name.
 */
public class DeleteEventCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = DeleteCommand.COMMAND_WORD
            + ": Deletes an event by name.\n"
            + "Parameters: " + DeleteCommand.COMMAND_WORD + "[Event name] (must not be empty)\n"
            + "Example: " + DeleteCommand.COMMAND_WORD + PREFIX_EVENT + "Orientation";

    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Deleted Event: %1$s";


    private final EventName eventName;

    public DeleteEventCommand(EventName eventName) {
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Event toDelete = model.getEvent(eventName);

        if (toDelete == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_NAME, eventName));
        }

        model.deleteEvent(toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_EVENT_SUCCESS, eventName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventCommand // instanceof handles nulls
                && eventName.equals(((DeleteEventCommand) other).eventName)); // state check
    }

}
