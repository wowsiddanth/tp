package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;

import nustracker.model.AddressBook;
import nustracker.model.Model;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model, CurrentlyShownList currentlyShownList) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
