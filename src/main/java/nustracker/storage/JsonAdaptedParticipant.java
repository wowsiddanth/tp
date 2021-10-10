package nustracker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import nustracker.commons.exceptions.IllegalValueException;
import nustracker.model.event.Participant;
import nustracker.model.student.NusNetId;

/**
 * Jackson-friendly version of {@link Participant}.
 */
public class JsonAdaptedParticipant {

    private final String nusNetId;

    /**
     * Constructs a {@code JsonAdaptedParticipant} with the given {@code nusNetId}.
     */
    @JsonCreator
    public JsonAdaptedParticipant(String participantName) {
        this.nusNetId = participantName;
    }

    /**
     * Converts a given {@code JsonAdaptedParticipant} into this class for Jackson use.
     */
    public JsonAdaptedParticipant(Participant source) {
        nusNetId = source.nusNetId;
    }

    @JsonValue
    public String getNusNetId() {
        return nusNetId;
    }

    /**
     * Converts this Jackson-friendly adapted participant object into the model's {@code JsonAdaptedParticipant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participant.
     */
    public Participant toModelType() throws IllegalValueException {
        if (!NusNetId.isValidNusNetId(nusNetId)) {
            throw new IllegalValueException(NusNetId.MESSAGE_CONSTRAINTS);
        }
        return new Participant(nusNetId);
    }
}
