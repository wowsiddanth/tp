package seedu.address.model.event;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EventDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventTime(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EventDate(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null phone number
        assertThrows(NullPointerException.class, () -> EventDate.isValidDate(null));

        // invalid phone numbers
        Assertions.assertFalse(EventDate.isValidDate("")); // empty string
        Assertions.assertFalse(EventDate.isValidDate(" ")); // spaces only
        Assertions.assertFalse(EventDate.isValidDate("2021 09 27")); // spaces instead of dash
        Assertions.assertFalse(EventDate.isValidDate("20210927")); // no dashes
        Assertions.assertFalse(EventDate.isValidDate("2021-ab-27")); // alphabets within digits
        Assertions.assertFalse(EventDate.isValidDate("2021-13-21")); // invalid month
        Assertions.assertFalse(EventDate.isValidDate("2021-00-21")); // invalid month
        Assertions.assertFalse(EventDate.isValidDate("2021-10-32")); // invalid day
        Assertions.assertFalse(EventDate.isValidDate("2021-10-00")); // invalid day

        // valid phone numbers
        Assertions.assertTrue(EventDate.isValidDate("2021-09-27")); // current date
        Assertions.assertTrue(EventDate.isValidDate("3000-12-31")); // late date
        Assertions.assertTrue(EventDate.isValidDate("1900-01-01")); // early date
    }

}
