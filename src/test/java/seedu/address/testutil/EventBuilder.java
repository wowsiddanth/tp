package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Participant;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Test event";
    public static final String DEFAULT_DATE = "2021-09-26";
    public static final String DEFAULT_TIME = "1530";

    private EventName name;
    private EventDate date;
    private EventTime time;
    private Set<Participant> participants;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_NAME);
        date = new EventDate(DEFAULT_DATE);
        time = new EventTime(DEFAULT_TIME);
        participants = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event event) {
        name = event.getName();
        date = event.getDate();
        time = event.getTime();
        participants = event.getParticipants();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new EventName(name);
        return this;
    }

    /**
     * Parses the {@code participants} into a {@code Set<Participants>} and set it to the {@code Event} that we are
     * building.
     */
    public EventBuilder withParticipants(String ... participants) {
        this.participants = SampleDataUtil.getParticipantSet(participants);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new EventDate(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Event} that we are building.
     */
    public EventBuilder withTime(String time) {
        this.time = new EventTime(time);
        return this;
    }

    public Event build() {
        return new Event(name, date, time, participants);
    }

}
