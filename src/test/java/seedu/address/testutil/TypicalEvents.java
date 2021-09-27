package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENTDATE_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENTDATE_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENTNAME_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENTNAME_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENTTIME_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENTTIME_TEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPANT_TEST;

import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event ORIENTATION = new EventBuilder().withName("Orientation Camp")
            .withDate("2022-08-01")
            .withTime("0900")
            .withParticipants("Freshman 1", "Freshman 2").build();
    public static final Event SPORTS_CAMP = new EventBuilder().withName("Sports Camp")
            .withDate("2022-09-15")
            .withTime("1200")
            .withParticipants("Athlete 1", "Athlete 2", "Athlete 3").build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event TEST = new EventBuilder().withName(VALID_EVENTNAME_TEST).withDate(VALID_EVENTDATE_TEST)
            .withTime(VALID_EVENTTIME_TEST).withParticipants(VALID_PARTICIPANT_TEST).build();
    public static final Event FINAL = new EventBuilder().withName(VALID_EVENTNAME_FINAL).withDate(VALID_EVENTDATE_FINAL)
            .withTime(VALID_EVENTTIME_FINAL).withParticipants(VALID_PARTICIPANT_FINAL).build();

    private TypicalEvents() {} // prevents instantiation
}
