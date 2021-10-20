package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.student.StudentIdContainsKeywordsPredicate;

/**
 * Filters and lists all students in address book whose student ID contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterIdCommand extends FilterCommand {

    private final StudentIdContainsKeywordsPredicate predicate;

    public FilterIdCommand(StudentIdContainsKeywordsPredicate predicate) {
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
                || (other instanceof FilterIdCommand // instanceof handles nulls
                && predicate.equals(((FilterIdCommand) other).predicate)); // state check
    }
}
