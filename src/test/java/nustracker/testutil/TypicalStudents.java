package nustracker.testutil;

import nustracker.model.AddressBook;
import nustracker.model.student.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nustracker.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_NUSNETID_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_NUSNETID_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static nustracker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static nustracker.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_YEAR_BOB;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withYear("1")
            .withMajor("CS")
            .withNusNetId("e9034800")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withYear("3")
            .withMajor("IS")
            .withNusNetId("e8123198")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withPhone("95352563")
            .withYear("4")
            .withMajor("ISEC")
            .withNusNetId("e8123081").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withYear("2")
            .withMajor("BZA")
            .withNusNetId("e9012390")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("94822248")
            .withYear("3")
            .withMajor("CS")
            .withNusNetId("e9831818")
            .withEmail("werner@example.com").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withYear("4")
            .withMajor("CS")
            .withNusNetId("e9192390")
            .withEmail("lydia@example.com").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("94824112")
            .withYear("2")
            .withMajor("IS")
            .withNusNetId("e9123119")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com")
            .withYear("2")
            .withMajor("CS")
            .withNusNetId("e9193111")
            .withTags("friends").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com")
            .withYear("3")
            .withMajor("CS")
            .withNusNetId("e9131111")
            .build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withYear(VALID_YEAR_AMY)
            .withMajor(VALID_MAJOR_AMY)
            .withNusNetId(VALID_NUSNETID_AMY)
            .withTags(VALID_TAG_FRIEND)
            .build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withYear(VALID_YEAR_BOB)
            .withMajor(VALID_MAJOR_BOB)
            .withNusNetId(VALID_NUSNETID_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
