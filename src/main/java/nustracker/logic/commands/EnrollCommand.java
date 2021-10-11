package nustracker.logic.commands;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_EVENT_NAME;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_STUDENT_NUSNETID;
import static nustracker.commons.util.CollectionUtil.requireAllNonNull;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NUSNETID;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nustracker.commons.core.Messages;
import nustracker.commons.core.index.Index;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.event.Event;
import nustracker.model.event.EventName;
import nustracker.model.event.Participant;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.NusNetId;
import nustracker.model.student.Student;

/**
 * Adds an existing person to an event.
 */
public class EnrollCommand extends Command {

    public static final String COMMAND_WORD = "enroll";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the person identified by NUS NetId to an event "
            + "identified by its name. \n"
            + "Parameters:"
            + PREFIX_NUSNETID +"NUSNETID "
            + PREFIX_EVENT + "EVENT_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NUSNETID + "e0322322 "
            + PREFIX_EVENT + "Orientation Camp";
    public static final String MESSAGE_ADD_EVENT_SUCCESS = "Enrolled Student: %1$s into the Event: %2$s";
    public static final String MESSAGE_STUDENT_ALREADY_ENROLLED =
            "The Student %1$s is already enrolled into the event: %2$s";

    private final NusNetId nusNetId;
    private final EventName eventName;

    /**
     * @param nusNetId of the student to enroll into the event
     * @param enrolledEvents the person is to be added to
     */
    public EnrollCommand(NusNetId nusNetId, EventName enrolledEvents) {
        requireAllNonNull(nusNetId, enrolledEvents);

        this.nusNetId = nusNetId;
        this.eventName = enrolledEvents;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

//        if (n.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
//        }

        //Enroll Student to event (4 Cases)
        //1. Success
        //2. Student does not exist
        //3. Event does not exist
        //4. Student already in event

        // Check if a student with this NUS NetID exists in the list here
        Student currStudent = model.getStudent(nusNetId);

        if (currStudent == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENT_NUSNETID, nusNetId.getNusNetIdString()));
        }

        // Check if an event with this event name exists here
        Event currEvent = model.getEvent(eventName);

        if (currEvent == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_EVENT_NAME, eventName.getEventName()));
        }

        // Check if student is already in event
        boolean isAlreadyInEvent = currEvent.isInEvent(nusNetId);

        if (isAlreadyInEvent) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ALREADY_ENROLLED,
                    currStudent.getName().toString(), currEvent.getName().getEventName()));
        }

        // Enroll the student into the event by:
        // 1. Add into this student's EnrolledEvents in model
        // 2. Add to event list
        // 3. If there is an error then remove from this student's EnrolledEvents in model (PREVENT BUGS)

        EnrolledEvents currentlyEnrolledEvents = currStudent.getEvents();
        EnrolledEvents updatedEnrolledEvents = currentlyEnrolledEvents.enrollIntoEvent(currEvent);

        Student editedStudent = new Student(
                currStudent.getName(), currStudent.getPhone(), currStudent.getEmail(),
                currStudent.getYear(), currStudent.getMajor(), currStudent.getNusNetId(),
                currStudent.getTags(), updatedEnrolledEvents);

        model.setStudent(currStudent, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);


        Set<Participant> oldParticipants = currEvent.getParticipants();

        Set<Participant> updatedParticipants = new HashSet<>(oldParticipants);
        updatedParticipants.add(new Participant(currStudent.getNusNetId().getNusNetIdString()));

        Event updatedEvent = new Event(
                currEvent.getName(), currEvent.getDate(), currEvent.getTime(), updatedParticipants
        );

        model.setEvent(currEvent, updatedEvent);

        return new CommandResult(String.format(MESSAGE_ADD_EVENT_SUCCESS, currStudent, currEvent));
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
        return nusNetId.equals(e.nusNetId)
                && eventName.equals(e.eventName);
    }
}
