package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Participant;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
public class JsonAdaptedParticipant {

    private final String participantName;

    /**
     * Constructs a {@code JsonAdaptedParticipant} with the given {@code participantName}.
     */
    @JsonCreator
    public JsonAdaptedParticipant(String participantName) {
        this.participantName = participantName;
    }

    /**
     * Converts a given {@code Participant} into this class for Jackson use.
     */
    public JsonAdaptedParticipant(Participant source) {
        participantName = source.name;
    }

    @JsonValue
    public String getParticipantName() {
        return participantName;
    }

    /**
     * Converts this Jackson-friendly adapted participant object into the model's {@code Participant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participant.
     */
    public Participant toModelType() throws IllegalValueException {
        if (!Participant.isValidName(participantName)) {
            throw new IllegalValueException(Participant.MESSAGE_CONSTRAINTS);
        }
        return new Participant(participantName);
    }

}
