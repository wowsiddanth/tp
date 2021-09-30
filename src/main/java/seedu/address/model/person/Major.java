package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Represents a Student's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Majors should only contain alphabets, and is case sensitive!"
            + "\n The valid computing majors are: CS (Computer Science), IS (Information Systems)"
            + ", ISEC (Information Security), and BZA (Business Analytics)";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";

    /*
     * The HashMap that maps the major, to the list of students that are currently
     * in that major.
     */
    private static final HashMap<Major, ArrayList<Person>> studentsToMajors = new HashMap<>();

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param major A valid major.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        value = major.toUpperCase();
    }

    /**
     * Adds a student to the array list corresponding to that student's major
     *
     * @param student The student to be added
     */
    public static void addStudent(Person student) {
        Major majorOfStudent = student.getMajor();
        Optional<ArrayList<Person>> currStudents = Optional.ofNullable(studentsToMajors.get(majorOfStudent));

        if (currStudents.isEmpty()) {
            ArrayList<Person> studentsInMajor = new ArrayList<>();
            studentsInMajor.add(student);
            studentsToMajors.put(majorOfStudent, studentsInMajor);
        } else {
            currStudents.get().add(student);
            studentsToMajors.put(majorOfStudent, currStudents.get());
        }
    }

    /**
     * Remove student from the arraylist corresponding to the student's major
     *
     * @param student The student to be removed
     */
    public static void removeStudent(Person student) {
        Major majorOfStudent = student.getMajor();
        ArrayList<Person> currStudents = studentsToMajors.get(majorOfStudent);
        currStudents.remove(student);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMajor(String test) {
        boolean matchesRegex = test.matches(VALIDATION_REGEX);
        boolean validCourse = isValidMajorName(test);
        return matchesRegex && validCourse;
    }

    /**
     * Checks if the given major string is a valid major name
     *
     * @param major The major given
     * @return True if valid, false if invalid
     */
    public static boolean isValidMajorName(String major) {
        String nameOfMajor = major.toUpperCase(); //Not case-sensitive
        return nameOfMajor.equals("CS") || nameOfMajor.equals("IS")
                || nameOfMajor.equals("ISEC") || nameOfMajor.equals("BZA");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && value.equals(((Major) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
