package nustracker.logic.parser;

import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.ExportCommand;
import nustracker.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {

    @Override
    public ExportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);


        if (!argMultimap.arePrefixesPresent(PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportCommand.MESSAGE_USAGE));
        }

        String fileName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName;

        return new ExportCommand(fileName);
    }
}


