package seedu.address.model.person;

import com.fasterxml.jackson.databind.ext.CoreXMLDeserializers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS =
            "Majors should only contain alphabets, and is case sensitive!" +
            "\n The valid computing majors are: CS, IS, ISEC, BZA";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static HashMap<Major, ArrayList<Person>> studentsToMajors = new HashMap<>();

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param major A valid major.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        value = major;
    }

    public static void addStudent(Person person) {
        Major majorOfStudent = person.getMajor();
        Optional<ArrayList<Person>> currStudents = Optional.ofNullable(studentsToMajors.get(majorOfStudent));
        if (currStudents.isEmpty()) {
            ArrayList<Person> studentsInMajor = new ArrayList<>();
            studentsInMajor.add(person);
            studentsToMajors.put(majorOfStudent, studentsInMajor);
        } else {
            currStudents.get().add(person);
            studentsToMajors.put(majorOfStudent, currStudents.get());
        }
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMajor(String test) {
        boolean matchesRegex =  test.matches(VALIDATION_REGEX);
        boolean validCourse = isValidMajorName(test);
        return matchesRegex && validCourse;
    }

    public static boolean isValidMajorName(String course) {
        return course.equals("CS") || course.equals("IS") ||course.equals("ISEC") || course.equals("BZA");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && value.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}