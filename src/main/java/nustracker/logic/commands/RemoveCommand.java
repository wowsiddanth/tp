package nustracker.logic.commands;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.event.Participant;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.NusNetId;
import nustracker.model.student.Student;

import java.util.HashSet;
import java.util.Set;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_STUDENT_NUSNETID;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NUSNETID;

public class RemoveCommand extends Command {


    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the person identified by NUS NetId from an event "
            + "identified by its name. \n"
            + "Parameters: "
            + PREFIX_NUSNETID + "NUSNETID "
            + PREFIX_EVENT + "EVENT_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NUSNETID + "e0322322 "
            + PREFIX_EVENT + "Orientation Camp";
    public static final String MESSAGE_REMOVE_EVENT_SUCCESS =
            "Removed Student: %1$s with NUS NetId %2$s from the Event: %3$s";
    public static final String MESSAGE_STUDENT_NOT_ALREADY_ENROLLED =
            "The Student %1$s with NUS NetId %2$s cannot be removed from the event: %3$s as he was not enrolled yet";

    private final NusNetId nusNetId;
    private final EventName eventName;

    /**
     * @param nusNetId of the student to remove from the event
     * @param eventName the person is to be removed
     */
    public RemoveCommand(NusNetId nusNetId, EventName eventName) {
        requireAllNonNull(nusNetId, eventName);

        this.nusNetId = nusNetId;
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        // Remove Student from event (4 Cases)
        // 1. Success
        // 2. Student does not exist
        // 3. Event does not exist
        // 4. Student not already in event

        // Check if a student with this NUS NetID exists in the list
        Student currStudent = model.getStudent(nusNetId);

        if (currStudent == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENT_NUSNETID, nusNetId.getNusNetIdString()));
        }

        // Check if an event with this event name exists
        Event currEvent = model.getEvent(eventName);

        if (currEvent == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_NAME, eventName.getEventName()));
        }

        // Check if student is already in event
        boolean isAlreadyInEvent = currEvent.isInEvent(nusNetId);

        if (!isAlreadyInEvent) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_ALREADY_ENROLLED,
                    currStudent.getName().toString(),
                    currStudent.getNusNetId().getNusNetIdString(),
                    currEvent.getName().getEventName()));
        }

        // Remove the student into the event by:
        // 1. Remove from this student's EnrolledEvents in model
        // 2. Remove from event list

        EnrolledEvents currentlyEnrolledEvents = currStudent.getEvents();
        EnrolledEvents updatedEnrolledEvents = currentlyEnrolledEvents.removeFromEvent(currEvent);

        Student editedStudent = new Student(
                currStudent.getName(), currStudent.getPhone(), currStudent.getEmail(),
                currStudent.getYear(), currStudent.getMajor(), currStudent.getNusNetId(),
                currStudent.getTags(), updatedEnrolledEvents);

        model.setStudent(currStudent, editedStudent);

        Set<Participant> oldParticipants = currEvent.getParticipants();

        Set<Participant> updatedParticipants = new HashSet<>();

        for (Participant currParticipant : oldParticipants) {
            if (currParticipant.getNusNetId().equals(nusNetId)) {
                // Do not add this participant to remove it.
            } else {
                updatedParticipants.add(currParticipant);
            }
        }



        Event updatedEvent = new Event(
                currEvent.getName(), currEvent.getDate(), currEvent.getTime(), updatedParticipants
        );

        model.setEvent(currEvent, updatedEvent);

        return new CommandResult(String.format(MESSAGE_REMOVE_EVENT_SUCCESS,
                editedStudent.getName().toString(),
                editedStudent.getNusNetId().getNusNetIdString(),
                updatedEvent.getName().getEventName()));
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        // state check
        RemoveCommand e = (RemoveCommand) other;
        return nusNetId.equals(e.nusNetId)
                && eventName.equals(e.eventName);
    }

}
