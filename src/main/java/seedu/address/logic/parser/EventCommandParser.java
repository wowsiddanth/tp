package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EnrollCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.EnrolledEvents;

public class EventCommandParser implements Parser<EnrollCommand> {

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
