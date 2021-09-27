package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Event {

    private final EventName name;
    private final EventDate date;
    private final EventTime time;

    private final Set<Participant> participants = new HashSet<>();

    /**
     * Create an Event with no participants.
     *
     * @param name Event name
     * @param date Event date
     * @param time Event time
     */
    public Event(EventName name, EventDate date, EventTime time) {
        requireAllNonNull(name, date, time);
        this.name = name;
        this.date = date;
        this.time = time;
    }

    /**
     * Create an Event with a set of Participants.
     *
     * @param name Event name
     * @param date Event date
     * @param time Event time
     * @param participants Event participants
     */
    public Event(EventName name, EventDate date, EventTime time, Set<Participant> participants) {
        requireAllNonNull(name, date, time, participants);
        this.name = name;
        this.date = date;
        this.time = time;
        this.participants.addAll(participants);
    }

    public EventName getName() {
        return name;
    }

    public EventDate getDate() {
        return date;
    }

    public EventTime getTime() {
        return time;
    }

    /**
     * Returns an immutable participant set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Participant> getParticipants() {
        return Collections.unmodifiableSet(participants);
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * Participants enrolled in the two events are not compared.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;

        return otherEvent.getName().equals(getName())
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, time, participants);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Date: ")
                .append(getDate())
                .append("; Time: ")
                .append(getTime());

        Set<Participant> participants = getParticipants();
        if (!participants.isEmpty()) {
            builder.append("; Participants: ");
            participants.forEach(builder::append);
        }

        return builder.toString();
    }
}
