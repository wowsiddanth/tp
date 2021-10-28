package nustracker.logic.commands;

import org.junit.jupiter.api.Test;

import nustracker.model.Model;
import nustracker.model.ModelManager;

class EventsCommandTest {

    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Test
    void execute_events_success() {
        CommandResult expectedCommandResult = new CommandResult(EventsCommand.MESSAGE_SHOW_EVENTS_SUCCESS,
                false, false, false, false, false, false, true);
        CommandTestUtil.assertCommandSuccess(new EventsCommand(), model, expectedCommandResult, expectedModel);
    }
}
