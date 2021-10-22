package nustracker.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustracker.logic.parser.CliSyntax.PREFIX_FILENAME;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.ExportCommand;
import nustracker.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {

    @Override
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILENAME);

        if (!argMultimap.arePrefixesPresent(PREFIX_FILENAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportCommand.MESSAGE_USAGE));
        }

        String fileName = ParserUtil.parseExportFileName(argMultimap.getValue(PREFIX_FILENAME).get());

        return new ExportCommand(fileName);
    }
}


