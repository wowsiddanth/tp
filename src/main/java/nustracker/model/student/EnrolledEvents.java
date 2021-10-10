package nustracker.model.student;

import nustracker.model.event.Event;
import nustracker.model.event.EventName;

import java.util.HashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * This class represent any event(s) that a person is a part of.
 */
public class EnrolledEvents {

    public static final String MESSAGE_CONSTRAINTS = "Events can take any values, and it should not be blank";

    // Design:
    // We use a HashMap with EventName as keys because events are usually found by it's name.
    // If we use a Set, we have to get the event object first in order to search for it.
    // We can do this because all events have unique names.
    private HashMap<EventName, Event> eventsEnrolledIn;

    /**
     * Constructs a new {@code EnrolledEvents}.
     */
    public EnrolledEvents() {
        eventsEnrolledIn = new HashMap<>();
    }

    /**
     * Adds an {@code Event} into this student's enrolled events.
     *
     * @param event A valid event to be added.
     */
    public void enrollIntoEvent(Event event) {
        requireNonNull(event);
        EventName eventName = event.getName();

        // Enroll student into Event object here

        eventsEnrolledIn.put(eventName,event);
    }

    /**
     * Removes an {@code Event} from this student's enrolled events.
     *
     * @param event A valid event to be removed.
     */
    public void removeFromEvent(Event event) {
        requireNonNull(event);
        EventName eventName = event.getName();
        eventsEnrolledIn.remove(eventName);

        // Remove student from Event object here

    }

    @Override
    public String toString() {
        // Prints list of events nicely separated by commas
        return eventsEnrolledIn.values().stream().map(Object::toString).collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnrolledEvents // instanceof handles nulls
                && eventsEnrolledIn.equals(((EnrolledEvents) other).eventsEnrolledIn)); // state check
    }

    // If 2 students are enrolled in the same set of events they will have the same hash code.
    @Override
    public int hashCode() {
        return eventsEnrolledIn.hashCode();
    }
}
