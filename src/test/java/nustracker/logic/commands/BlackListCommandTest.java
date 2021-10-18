package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;
import static nustracker.testutil.Assert.assertThrows;
import static nustracker.testutil.TypicalEvents.EVENTNAME_ONE;
import static nustracker.testutil.TypicalEvents.ORIENTATION;
import static nustracker.testutil.TypicalStudents.NUSNETID_ONE;
import static nustracker.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.AddressBook;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.UserPrefs;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.event.Participant;
import nustracker.model.student.NusNetId;
import nustracker.testutil.EventBuilder;
import nustracker.testutil.ModelStub;

class BlackListCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BlackListCommand(null, null));
        assertThrows(NullPointerException.class, () -> new BlackListCommand(
                new NusNetId("e1234567"), null));
        assertThrows(NullPointerException.class, () -> new BlackListCommand(
                null, new EventName("Valid event name")));
    }

    @Test
    public void execute_blacklistAccepted_addSuccessful() throws Exception {

        CommandResult commandResult = new BlackListCommand(NUSNETID_ONE, ORIENTATION.getName()).execute(model);

        Assertions.assertEquals(String.format(BlackListCommand.MESSAGE_BLACKLIST_SUCCESS, NUSNETID_ONE, EVENTNAME_ONE),
                commandResult.getFeedbackToUser());

        // Change blacklist of event in modelStub
        ModelStubSetEventBlacklist modelStub = new ModelStubSetEventBlacklist();
        ArrayList<String> newBlacklist =
                new ArrayList<>(ORIENTATION
                        .getBlacklist().stream().map(Participant::toString).collect(Collectors.toList()));
        newBlacklist.add(NUSNETID_ONE.toString());
        modelStub.setBlacklist(ORIENTATION, newBlacklist);

        Assertions.assertEquals(modelStub.addressBook, model.getAddressBook());
    }

    @Test
    public void execute_blacklistRejected_studentIdAlreadyBlacklisted() throws Exception {
        Participant blacklisted = (Participant) ORIENTATION.getBlacklist().toArray()[0];

        assertThrows(CommandException.class,
                String.format(BlackListCommand.MESSAGE_STUDENTID_ALREADY_BLACKLISTED,
                        blacklisted.getNusNetId(), ORIENTATION.getName()), () ->
                        new BlackListCommand(blacklisted.getNusNetId(), ORIENTATION.getName()).execute(model));
    }

    @Test
    public void execute_blacklistRejected_eventDoesNotExist() throws Exception {
        EventName nonExistentEventName = new EventName("This event does not exist");

        assertThrows(CommandException.class,
                String.format(MESSAGE_INVALID_EVENT_NAME, nonExistentEventName.toString()), () ->
                        new BlackListCommand(NUSNETID_ONE, nonExistentEventName).execute(model));
    }

    /**
     * A Model stub that always sets the event to be edited.
     */
    private class ModelStubSetEventBlacklist extends ModelStub {
        final AddressBook addressBook = getTypicalAddressBook();

        @Override
        public void setEvent(Event target, Event editedEvent) {
            requireAllNonNull(target, editedEvent);

            addressBook.setEvent(target, editedEvent);
        }

        public void setBlacklist(Event target, List<String> blacklist) {
            Event withNewBlacklist = new EventBuilder(target)
                    .withBlacklist(blacklist.toArray(new String[0]))
                    .build();
            setEvent(target, withNewBlacklist);
        }

        @Override
        public Event getEvent(EventName name) {
            requireNonNull(name);

            return addressBook.getEvent(name);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
