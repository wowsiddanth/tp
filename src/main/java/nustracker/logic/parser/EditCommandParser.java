package nustracker.logic.parser;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustracker.logic.parser.CliSyntax.PREFIX_EMAIL;
import static nustracker.logic.parser.CliSyntax.PREFIX_MAJOR;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_PHONE;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static nustracker.logic.parser.CliSyntax.PREFIX_YEAR;

import nustracker.logic.commands.EditCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.student.StudentId;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_YEAR, PREFIX_STUDENTID, PREFIX_MAJOR);

        if (!argMultimap.arePrefixesPresent(PREFIX_STUDENTID) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        StudentId studentIdToEdit = ParserUtil.parseStudenttId(argMultimap.getAllValues(PREFIX_STUDENTID).get(0));



        EditCommand.EditStudentDescriptor editStudentDescriptor = new EditCommand.EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_YEAR).isPresent()) {
            editStudentDescriptor.setYear(ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get()));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editStudentDescriptor.setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            editStudentDescriptor.setStudentId(ParserUtil.parseStudenttId(
                    argMultimap.getValue(PREFIX_STUDENTID).get()));
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(studentIdToEdit, editStudentDescriptor);
    }

}
