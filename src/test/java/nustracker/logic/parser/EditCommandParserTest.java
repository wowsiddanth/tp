package nustracker.logic.parser;

import static nustracker.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static nustracker.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.CommandTestUtil;
import nustracker.logic.commands.EditCommand;
import nustracker.model.student.Email;
import nustracker.model.student.Name;
import nustracker.model.student.Phone;
import nustracker.model.student.StudentId;
import nustracker.testutil.EditStudentDescriptorBuilder;



public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, STUDENTID_DESC_BOB,
              EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5"
                + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0"
                + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, STUDENTID_DESC_AMY
                + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        CommandParserTestUtil.assertParseFailure(parser, STUDENTID_DESC_AMY
                + CommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        CommandParserTestUtil.assertParseFailure(parser, STUDENTID_DESC_AMY
                + CommandTestUtil.INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email


        // invalid phone followed by valid email
        CommandParserTestUtil.assertParseFailure(parser, STUDENTID_DESC_AMY
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        CommandParserTestUtil.assertParseFailure(parser, STUDENTID_DESC_AMY
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);


        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, STUDENTID_DESC_AMY
                  + CommandTestUtil.INVALID_NAME_DESC
                        + CommandTestUtil.INVALID_EMAIL_DESC + CommandTestUtil.VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String studentIdStr = VALID_STUDENTID_AMY;
        String userInput = STUDENTID_DESC_AMY
                + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.NAME_DESC_AMY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(
                        CommandTestUtil.VALID_NAME_AMY)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_AMY)
                .build();
        EditCommand expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String studentIdStr = VALID_STUDENTID_AMY;
        String userInput = STUDENTID_DESC_AMY + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_AMY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(
                        CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String studentIdStr = VALID_STUDENTID_BOB;
        String userInput = STUDENTID_DESC_BOB + CommandTestUtil.NAME_DESC_AMY;
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(
                CommandTestUtil.VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = STUDENTID_DESC_BOB + CommandTestUtil.PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = STUDENTID_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);


    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String studentIdStr = VALID_STUDENTID_AMY;
        String userInput = STUDENTID_DESC_AMY + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(
                        CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String studentIdStr = VALID_STUDENTID_AMY;
        String userInput = STUDENTID_DESC_AMY + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.PHONE_DESC_BOB;
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(
                CommandTestUtil.VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = STUDENTID_DESC_AMY + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(
                CommandTestUtil.VALID_EMAIL_BOB).build();
        expectedCommand = new EditCommand(new StudentId(studentIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }


}
