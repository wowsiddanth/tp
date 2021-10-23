package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import nustracker.commons.core.Messages;
import nustracker.model.Model;
import nustracker.model.student.MajorContainsKeywordsPredicate;
import nustracker.ui.MainWindow;

/**
 * Filters and lists all students in address book whose major contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterMajorCommand extends FilterCommand {

    private final MajorContainsKeywordsPredicate predicate;

    public FilterMajorCommand(MajorContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, MainWindow.CurrentlyShownList currentlyShownList) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterMajorCommand // instanceof handles nulls
                && predicate.equals(((FilterMajorCommand) other).predicate)); // state check
    }
}
