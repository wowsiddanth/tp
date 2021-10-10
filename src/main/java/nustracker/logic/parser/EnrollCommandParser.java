package nustracker.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;

import nustracker.commons.core.index.Index;
import nustracker.commons.exceptions.IllegalValueException;
import nustracker.logic.commands.EnrollCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.student.EnrolledEvents;

public class EnrollCommandParser implements Parser<EnrollCommand> {

    @Override
    public EnrollCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_EVENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EnrollCommand.MESSAGE_USAGE), ive);
        }

        EnrolledEvents enrolledEvents = new EnrolledEvents(argMultimap.getValue(PREFIX_EVENT).orElse(""));

        return new EnrollCommand(index, enrolledEvents);
    }
}
