package nustracker.logic.parser;

import static nustracker.logic.parser.CliSyntax.PREFIX_EMAIL;
import static nustracker.logic.parser.CliSyntax.PREFIX_MAJOR;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_NUSNETID;
import static nustracker.logic.parser.CliSyntax.PREFIX_PHONE;
import static nustracker.logic.parser.CliSyntax.PREFIX_TAG;
import static nustracker.logic.parser.CliSyntax.PREFIX_YEAR;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.AddCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.student.Email;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.NusNetId;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.Year;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_YEAR, PREFIX_MAJOR, PREFIX_NUSNETID, PREFIX_TAG);

        if (!argMultimap.arePrefixesPresent(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_YEAR, PREFIX_MAJOR, PREFIX_NUSNETID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        Major major = ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get());
        NusNetId nusNetId = ParserUtil.parseNusNetId(argMultimap.getValue(PREFIX_NUSNETID).get());

        EnrolledEvents enrolledEvents = new EnrolledEvents();

        Student student = new Student(name, phone, email, year, major, nusNetId, enrolledEvents);

        return new AddCommand(student);
    }



}
