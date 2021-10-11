package nustracker.logic.parser;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_NUSNETID;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.FilterCommand;
import nustracker.logic.commands.FilterEventCommand;
import nustracker.logic.commands.FilterIdCommand;
import nustracker.logic.commands.FilterNameCommand;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;
import nustracker.model.student.NameContainsKeywordsPredicate;
import nustracker.model.student.NusNetIdContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterNameCommand() {
        FilterCommand expectedFilterCommand =
                new FilterNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterIdCommand() {
        FilterCommand expectedFilterCommand =
                new FilterIdCommand(new NusNetIdContainsKeywordsPredicate(Arrays.asList("e1234567", "e2345678")));
        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_NUSNETID + "e1234567 e2345678",
                expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_NUSNETID + " \n e1234567 \n \t e2345678  \t",
                expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterEventCommand() {
        FilterCommand expectedFilterCommand =
                new FilterEventCommand(new EnrolledEventsContainsKeywordsPredicate("event name"));
        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EVENT + "event name", expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EVENT + " \n\t event name \n",
                expectedFilterCommand);
    }



}
