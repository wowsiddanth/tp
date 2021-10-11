package nustracker.logic.commands;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.student.NameContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Filters and lists all students in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterNameCommand extends FilterCommand {

    private final NameContainsKeywordsPredicate predicate;

    public FilterNameCommand(NameContainsKeywordsPredicate predicate) {
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
                || (other instanceof FilterNameCommand // instanceof handles nulls
                && predicate.equals(((FilterNameCommand) other).predicate)); // state check
    }
}
