package nustracker.logic.parser;

import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_NUSNETID;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENT;
import static nustracker.testutil.TypicalEvents.EVENTNAME_ONE;
import static nustracker.testutil.TypicalStudents.NUSNETID_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.AddCommand;
import nustracker.logic.commands.ClearCommand;
import nustracker.logic.commands.CreateCommand;
import nustracker.logic.commands.DeleteCommand;
import nustracker.logic.commands.DeleteEventCommand;
import nustracker.logic.commands.DeleteStudentCommand;
import nustracker.logic.commands.EditCommand;
import nustracker.logic.commands.EventsCommand;
import nustracker.logic.commands.ExitCommand;
import nustracker.logic.commands.FilterCommand;
import nustracker.logic.commands.FilterEventCommand;
import nustracker.logic.commands.FilterIdCommand;
import nustracker.logic.commands.FilterNameCommand;
import nustracker.logic.commands.HelpCommand;
import nustracker.logic.commands.StudentsCommand;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.event.Event;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;
import nustracker.model.student.NameContainsKeywordsPredicate;
import nustracker.model.student.NusNetIdContainsKeywordsPredicate;
import nustracker.model.student.Student;
import nustracker.testutil.Assert;
import nustracker.testutil.EditStudentDescriptorBuilder;
import nustracker.testutil.EventBuilder;
import nustracker.testutil.EventUtil;
import nustracker.testutil.StudentBuilder;
import nustracker.testutil.StudentUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteStudentCommand studentCommand = (DeleteStudentCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + PREFIX_STUDENT + NUSNETID_ONE);
        assertEquals(new DeleteStudentCommand(NUSNETID_ONE), studentCommand);

        DeleteEventCommand eventCommand = (DeleteEventCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + PREFIX_EVENT + EVENTNAME_ONE);
        assertEquals(new DeleteEventCommand(EVENTNAME_ONE), eventCommand);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PREFIX_NUSNETID + NUSNETID_ONE.getNusNetIdString() + " "
                + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new EditCommand(NUSNETID_ONE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");

        FilterCommand nameCommand = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_NAME + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterNameCommand(new NameContainsKeywordsPredicate(keywords)), nameCommand);

        FilterCommand idCommand = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_NUSNETID + keywords.stream().collect(
                        Collectors.joining(" ")));
        assertEquals(new FilterIdCommand(new NusNetIdContainsKeywordsPredicate(keywords)), idCommand);

        FilterCommand eventCommand = (FilterCommand) parser.parseCommand(
                FilterCommand.COMMAND_WORD + " " + PREFIX_EVENT + "foo");
        assertEquals(new FilterEventCommand(new EnrolledEventsContainsKeywordsPredicate("foo")), eventCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_create() throws Exception {
        Event event = new EventBuilder().build();
        CreateCommand command = (CreateCommand) parser.parseCommand(EventUtil.getCreateCommand(event));
        assertEquals(new CreateCommand(event), command);
    }

    @Test
    public void parseCommand_students() throws Exception {
        assertTrue(parser.parseCommand(StudentsCommand.COMMAND_WORD) instanceof StudentsCommand);
        assertTrue(parser.parseCommand(StudentsCommand.COMMAND_WORD + " 3") instanceof StudentsCommand);
    }

    @Test
    public void parseCommand_events() throws Exception {
        assertTrue(parser.parseCommand(EventsCommand.COMMAND_WORD) instanceof EventsCommand);
        assertTrue(parser.parseCommand(EventsCommand.COMMAND_WORD + " 3") instanceof EventsCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        Assert.assertThrows(ParseException.class, Messages.MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
