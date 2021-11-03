package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_MULTIPLE_FILTER_FIELDS;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.FilterCommand;
import nustracker.logic.commands.FilterEventCommand;
import nustracker.logic.commands.FilterIdCommand;
import nustracker.logic.commands.FilterNameCommand;
import nustracker.model.event.EventName;
import nustracker.model.student.NameContainsKeywordsPredicate;
import nustracker.model.student.StudentIdContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPreamble() {
        CommandParserTestUtil.assertParseFailure(parser, " " + PREFIX_STUDENTID,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_multipleFileds() {
        CommandParserTestUtil.assertParseFailure(parser,
                " " + PREFIX_STUDENTID + "e0000000 " + PREFIX_NAME + " Alice", MESSAGE_MULTIPLE_FILTER_FIELDS);
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
                new FilterIdCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList("e1234567", "e2345678")));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_STUDENTID + "e1234567 e2345678",
                expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_STUDENTID + " \n e1234567 \n \t e2345678  \t",
                expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterEventCommand() {
        FilterCommand expectedFilterCommand =
                new FilterEventCommand(new EventName("event name"));
        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EVENT + "event name", expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EVENT + " \n\t event name \n",
                expectedFilterCommand);
    }



}
