package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.EnrolledEvents;
import seedu.address.model.person.Person;

/**
 * Adds an existing person to an event.
 */
public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the person identified to an event "
            + "by the index number used in the last person listing. "
            + "Existing event will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "e/ [EVENT]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "e/ Orientation camp.";
    public static final String MESSAGE_ADD_EVENT_SUCCESS = "Added event to Person: %1$s";
    public static final String MESSAGE_DELETE_EVENT_SUCCESS = "Removed event from Person: %1$s";

    private final Index index;
    private final EnrolledEvents enrolledEvents;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param enrolledEvents the person is to be added to
     */
    public EnrollCommand(Index index, EnrolledEvents enrolledEvents) {
        requireAllNonNull(index, enrolledEvents);

        this.index = index;
        this.enrolledEvents = enrolledEvents;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), enrolledEvents);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the event is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !enrolledEvents.value.isEmpty() ? MESSAGE_ADD_EVENT_SUCCESS : MESSAGE_DELETE_EVENT_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrollCommand)) {
            return false;
        }

        // state check
        EnrollCommand e = (EnrollCommand) other;
        return index.equals(e.index)
                && enrolledEvents.equals(e.enrolledEvents);
    }
}
