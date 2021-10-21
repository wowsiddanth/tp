package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.commons.core.Messages.MESSAGE_MULTIPLE_FILTER_FIELDS;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.Arrays;
import java.util.List;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.FilterCommand;
import nustracker.logic.commands.FilterEventCommand;
import nustracker.logic.commands.FilterIdCommand;
import nustracker.logic.commands.FilterNameCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;
import nustracker.model.student.NameContainsKeywordsPredicate;
import nustracker.model.student.StudentIdContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENTID, PREFIX_EVENT);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_STUDENTID, PREFIX_EVENT);
        long arguments = prefixes.stream().filter(prefix -> argMultimap.getValue(prefix).isPresent()).count();

        if (arguments > 1) {
            throw new ParseException(MESSAGE_MULTIPLE_FILTER_FIELDS);
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = getTrimmedArgs(args, PREFIX_NAME);
            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new FilterNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            String trimmedArgs = getTrimmedArgs(args, PREFIX_STUDENTID);
            String[] idKeywords = trimmedArgs.split("\\s+");
            return new FilterIdCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList(idKeywords)));
        } else if (argMultimap.getValue(PREFIX_EVENT).isPresent()) {
            String trimmedArgs = getTrimmedArgs(args, PREFIX_EVENT);
            return new FilterEventCommand(new EnrolledEventsContainsKeywordsPredicate(trimmedArgs));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }

    public String getTrimmedArgs(String args, Prefix prefix) throws ParseException {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(args, prefix);

        String trimmedArgs = argMap.getValue(prefix).get();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return trimmedArgs.trim();
    }

}
