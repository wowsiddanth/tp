package nustracker.logic.commands;

import org.junit.jupiter.api.Test;

import nustracker.model.Model;
import nustracker.model.ModelManager;


public class SettingsCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_settings_success() {
        CommandResult expectedCommandResult = new CommandResult(SettingsCommand.SHOWING_SETTINGS_MESSAGE,
                false, false, true, false, false, false, false);
        CommandTestUtil.assertCommandSuccess(new SettingsCommand(), model, expectedCommandResult, expectedModel);
    }
}
