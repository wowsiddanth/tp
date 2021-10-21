package nustracker.logic.commands;

import org.junit.jupiter.api.Test;

import nustracker.testutil.Assert;

class ExportCommandTest {

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new ExportCommand(null));
    }
}
