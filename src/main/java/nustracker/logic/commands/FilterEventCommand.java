package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;

/**
 * Filters and lists all students in address book attending the event in the argument.
 * Keyword matching is case insensitive.
 */
public class FilterEventCommand extends FilterCommand {

    private final EnrolledEventsContainsKeywordsPredicate predicate;

    public FilterEventCommand(EnrolledEventsContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
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
