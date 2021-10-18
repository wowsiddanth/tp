package nustracker.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.commons.exceptions.IllegalValueException;
import nustracker.model.student.Email;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.Phone;
import nustracker.model.student.StudentId;
import nustracker.model.student.Year;
import nustracker.testutil.Assert;
import nustracker.testutil.TypicalStudents;


public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_YEAR = "a";
    private static final String INVALID_MAJOR = "Computer Science";
    private static final String INVALID_STUDENTID = "e123123123123";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalStudents.BENSON.getName().toString();
    private static final String VALID_PHONE = TypicalStudents.BENSON.getPhone().toString();
    private static final String VALID_EMAIL = TypicalStudents.BENSON.getEmail().toString();
    private static final String VALID_YEAR = TypicalStudents.BENSON.getYear().toString();
    private static final String VALID_MAJOR = TypicalStudents.BENSON.getMajor().toString();
    private static final String VALID_STUDENTID = TypicalStudents.BENSON.getStudentId().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalStudents.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(TypicalStudents.BENSON);
        Assertions.assertEquals(TypicalStudents.BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_YEAR,
                        VALID_MAJOR, VALID_STUDENTID, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_MAJOR, VALID_YEAR,
                VALID_STUDENTID, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL,
                VALID_YEAR, VALID_MAJOR, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT,
                Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_YEAR, VALID_MAJOR, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null,
                VALID_YEAR, VALID_MAJOR, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT,
                Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_YEAR, VALID_MAJOR, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = Year.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullYear_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_MAJOR, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT,
                Year.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidMajor_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_YEAR, INVALID_MAJOR, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = Major.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullMajor_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_YEAR, null, VALID_STUDENTID, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT,
                Major.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_YEAR, VALID_MAJOR, INVALID_STUDENTID, VALID_TAGS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_YEAR, VALID_MAJOR, null, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT,
                StudentId.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_YEAR, VALID_MAJOR, VALID_STUDENTID, invalidTags);
        Assert.assertThrows(IllegalValueException.class, student::toModelType);
    }

}
