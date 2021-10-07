package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NusNetIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NusNetId(null));
    }

    @Test
    public void constructor_invalidNusNetId_throwsIllegalArgumentException() {
        String invalidNusNetId = "8471132";
        assertThrows(IllegalArgumentException.class, () -> new NusNetId(invalidNusNetId));
    }

    @Test
    public void isValidNusNetId() {
        //null NUS NetID
        assertThrows(NullPointerException.class, () -> new NusNetId(null));

        //invalid NUSNetIDs
        assertFalse(NusNetId.isValidNusNetId("e012323"));
        assertFalse(NusNetId.isValidNusNetId("e123133"));
        assertFalse(NusNetId.isValidNusNetId("e931138"));
        assertFalse(NusNetId.isValidNusNetId("e831639"));
        assertFalse(NusNetId.isValidNusNetId("a123199"));
        assertFalse(NusNetId.isValidNusNetId("b981239"));
        assertFalse(NusNetId.isValidNusNetId("e918329"));
        assertFalse(NusNetId.isValidNusNetId("e912812"));

        //valid majors
        assertTrue(NusNetId.isValidNusNetId("e1234897"));
        assertTrue(NusNetId.isValidNusNetId("e1234890"));
        assertTrue(NusNetId.isValidNusNetId("e8971232"));
        assertTrue(NusNetId.isValidNusNetId("e8912372"));
    }

}
