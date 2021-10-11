package nustracker.logic.parser;

import nustracker.model.student.NusNetId;
import nustracker.testutil.TypicalStudents;
import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.commons.core.index.Index;
import nustracker.logic.commands.CommandTestUtil;
import nustracker.logic.commands.EditCommand;
import nustracker.model.student.Email;
import nustracker.model.student.Name;
import nustracker.model.student.Phone;
import nustracker.model.tag.Tag;
import nustracker.testutil.EditStudentDescriptorBuilder;
import nustracker.testutil.TypicalIndexes;

import static nustracker.logic.commands.CommandTestUtil.*;


public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + CliSyntax.PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_BOB_WO_LEADING_SPACE, EditCommand.MESSAGE_NOT_EDITED);

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
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TAG_DESC_HUSBAND
                + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.TAG_DESC_FRIEND + TAG_EMPTY
                + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + TAG_EMPTY + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, NUSNETID_DESC_AMY_WO_LEADING_SPACE + CommandTestUtil.INVALID_NAME_DESC
                        + CommandTestUtil.INVALID_EMAIL_DESC + CommandTestUtil.VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String nusNetIdStr = VALID_NUSNETID_AMY;
        String userInput = NUSNETID_DESC_AMY_WO_LEADING_SPACE
                + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(
                        CommandTestUtil.VALID_NAME_AMY)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND, CommandTestUtil.VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String nusNetIdStr = VALID_NUSNETID_AMY;
        String userInput = NUSNETID_DESC_AMY_WO_LEADING_SPACE + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_AMY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(
                        CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String nusNetIdStr = VALID_NUSNETID_BOB;
        String userInput = NUSNETID_DESC_BOB_WO_LEADING_SPACE + CommandTestUtil.NAME_DESC_AMY;
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(
                CommandTestUtil.VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = NUSNETID_DESC_BOB_WO_LEADING_SPACE + CommandTestUtil.PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = NUSNETID_DESC_BOB_WO_LEADING_SPACE + CommandTestUtil.EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = NUSNETID_DESC_BOB_WO_LEADING_SPACE + CommandTestUtil.TAG_DESC_FRIEND;
        descriptor = new EditStudentDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String nusNetIdStr = VALID_NUSNETID_AMY;
        String userInput = NUSNETID_DESC_AMY_WO_LEADING_SPACE + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(
                        CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND,
                        CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String nusNetIdStr = VALID_NUSNETID_AMY;
        String userInput = NUSNETID_DESC_AMY_WO_LEADING_SPACE + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.PHONE_DESC_BOB;
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(
                CommandTestUtil.VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = NUSNETID_DESC_AMY_WO_LEADING_SPACE + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(
                CommandTestUtil.VALID_EMAIL_BOB).build();
        expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String nusNetIdStr = VALID_NUSNETID_AMY;
        String userInput = NUSNETID_DESC_AMY_WO_LEADING_SPACE + TAG_EMPTY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(new NusNetId(nusNetIdStr), descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }
}
