package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;

/**
 * Filters and lists all students in address book attending the event in the argument.
 */
public class FilterEventCommand extends FilterCommand {

    private final EnrolledEventsContainsKeywordsPredicate predicate;

    public FilterEventCommand(EnrolledEventsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Event event = model.getEvent(new EventName(predicate.getKeyword()));

        //event does not exist
        if (event == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_NAME, predicate.getKeyword()));
        }

        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterEventCommand // instanceof handles nulls
                && predicate.equals(((FilterEventCommand) other).predicate)); // state check
    }
}
