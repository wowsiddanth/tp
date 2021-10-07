package nustracker.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static nustracker.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_NUSNETID_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_NUSNETID_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static nustracker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static nustracker.logic.commands.CommandTestUtil.VALID_YEAR_AMY;
import static nustracker.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static nustracker.testutil.Assert.assertThrows;

import nustracker.testutil.Assert;
import nustracker.testutil.PersonBuilder;
import nustracker.testutil.TypicalPersons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new PersonBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void hasDuplicateCredentials() {
        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.hasDuplicateCredentials(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.hasDuplicateCredentials(null));

        // same nusnetid, phone number, email, all other attributes different -> returns true
        Student editedAmy = new PersonBuilder(TypicalPersons.AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withYear(VALID_YEAR_AMY)
                .withMajor(VALID_MAJOR_AMY)
                .withNusNetId(VALID_NUSNETID_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalPersons.AMY.hasDuplicateCredentials(editedAmy));

        // different nusnetid, all other attributes same -> returns false (since email and phone is the same)
        editedAmy = new PersonBuilder(TypicalPersons.ALICE).withNusNetId(VALID_NUSNETID_BOB).build();
        Assertions.assertTrue(TypicalPersons.ALICE.hasDuplicateCredentials(editedAmy));

        // different phone, all other attributes same -> returns true (since nusnetid and email is the same)
        editedAmy = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertTrue(TypicalPersons.ALICE.hasDuplicateCredentials(editedAmy));

        // different email, all other attributes same -> returns true (since nusnetid and phone is the same)
        editedAmy = new PersonBuilder(TypicalPersons.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertTrue(TypicalPersons.ALICE.hasDuplicateCredentials(editedAmy));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new PersonBuilder(TypicalPersons.ALICE).build();
        Assertions.assertTrue(TypicalPersons.ALICE.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalPersons.ALICE.equals(TypicalPersons.ALICE));

        // null -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(5));

        // different person -> returns false
        Assertions.assertFalse(TypicalPersons.ALICE.equals(TypicalPersons.BOB));

        // different name -> returns false
        Student editedAlice = new PersonBuilder(TypicalPersons.ALICE).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different phone -> returns true
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withEmail(VALID_EMAIL_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different year -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withYear(VALID_YEAR_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different major -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withMajor(VALID_MAJOR_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different nusnetid -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withNusNetId(VALID_NUSNETID_BOB).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(TypicalPersons.ALICE).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalPersons.ALICE.equals(editedAlice));
    }
}
