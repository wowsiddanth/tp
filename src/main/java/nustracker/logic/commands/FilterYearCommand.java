package nustracker.logic.commands;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.student.YearContainsKeywordsPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Filters and lists all students in address book whose year contains any of the argument keywords.
 */
public class FilterYearCommand extends FilterCommand {

    private final YearContainsKeywordsPredicate predicate;

    public FilterYearCommand(YearContainsKeywordsPredicate predicate) {
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
                || (other instanceof FilterYearCommand // instanceof handles nulls
                && predicate.equals(((FilterYearCommand) other).predicate)); // state check
    }
}
