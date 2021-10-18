package nustracker.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NUSNETID;

import nustracker.logic.commands.WhiteListCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.event.EventName;
import nustracker.model.student.NusNetId;

public class WhiteListCommandParser implements Parser<WhiteListCommand> {
    @Override
    public WhiteListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NUSNETID,
                PREFIX_EVENT);

        if (!argMultimap.arePrefixesPresent(PREFIX_NUSNETID, PREFIX_EVENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WhiteListCommand.MESSAGE_USAGE));
        }

        NusNetId nusNetId = ParserUtil.parseNusNetId(argMultimap.getValue(PREFIX_NUSNETID).get());
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT).get());

        return new WhiteListCommand(nusNetId, eventName);
    }
}
