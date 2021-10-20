package nustracker.testutil;

import static nustracker.testutil.TypicalEvents.ORIENTATION;
import static nustracker.testutil.TypicalEvents.SPORTS_CAMP;
import static nustracker.testutil.TypicalStudents.ALICE;
import static nustracker.testutil.TypicalStudents.BENSON;
import static nustracker.testutil.TypicalStudents.CARL;
import static nustracker.testutil.TypicalStudents.DANIEL;
import static nustracker.testutil.TypicalStudents.ELLE;
import static nustracker.testutil.TypicalStudents.FIONA;
import static nustracker.testutil.TypicalStudents.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import nustracker.model.AddressBook;
import nustracker.model.event.Event;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Student;

public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        for (Event event : getTypicalEvents()) {
            ab.addEvent(event);
            Set<Student> studentParticipants = event.getParticipantsAsStudents(ab);
            for (Student currStudent : studentParticipants) {
                if (currStudent == null) {
                    continue;
                }

                EnrolledEvents currentlyEnrolledEvents = currStudent.getEvents();
                EnrolledEvents updatedEnrolledEvents = currentlyEnrolledEvents.enrollIntoEvent(event);

                Student enrolledStudent = new Student(
                        currStudent.getName(), currStudent.getPhone(), currStudent.getEmail(),
                        currStudent.getYear(), currStudent.getMajor(), currStudent.getNusNetId(),
                         updatedEnrolledEvents);

                ab.setStudent(currStudent, enrolledStudent);
            }

        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(ORIENTATION, SPORTS_CAMP));
    }

}
