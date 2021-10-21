package nustracker.logic.commands;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;

/**
 * Displays the events list.
 */
public class EventsCommand extends Command {

    public static final String COMMAND_WORD = "events";
    public static final String MESSAGE_SHOW_EVENTS_SUCCESS =
            "Here is the list of events.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SHOW_EVENTS_SUCCESS, false, false,
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventsCommand)) {
            return false;
        }

        // state check
        return true;
    }
}
