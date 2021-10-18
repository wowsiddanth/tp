package nustracker.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nustracker.model.AddressBook;
import nustracker.model.ReadOnlyAddressBook;
import nustracker.model.event.Event;
import nustracker.model.event.EventDate;
import nustracker.model.event.EventName;
import nustracker.model.event.EventTime;
import nustracker.model.event.Participant;
import nustracker.model.student.Email;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.NusNetId;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.Year;
import nustracker.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Year("1"),
                new Major("CS"),
                new NusNetId("e1111111"),
                getTagSet("friends"),
                new EnrolledEvents()),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Year("2"),
                new Major("CS"),
                new NusNetId("e3223223"),
                getTagSet("colleagues", "friends"),
                new EnrolledEvents()),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Year("3"),
                new Major("CS"),
                new NusNetId("e1783902"),
                getTagSet("neighbours"),
                new EnrolledEvents()),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Year("4"),
                new Major("CS"),
                new NusNetId("e7307307"),
                getTagSet("family"),
                new EnrolledEvents()),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Year("1"),
                new Major("CS"),
                new NusNetId("e9654321"),
                getTagSet("classmates"),
                new EnrolledEvents()),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Year("2"),
                new Major("CS"),
                new NusNetId("e0542362"),
                getTagSet("colleagues"),
                new EnrolledEvents())
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new EventName("Orientation Camp"),
                    new EventDate("01-08-2022"),
                    new EventTime("0900"),
                    new HashSet<Participant>(List.of(
                        new Participant("e1111111"),
                        new Participant("e3223223")))
            ),
            new Event(new EventName("Sports Camp"),
                    new EventDate("10-09-2021"),
                    new EventTime("1200"),
                    new HashSet<Participant>(List.of(
                            new Participant("e3223223")))
            ),
            new Event(new EventName("Math Olympiad"),
                    new EventDate("05-03-2017"),
                    new EventTime("1615"),
                    new HashSet<Participant>(List.of(
                            new Participant("e0542362"),
                            new Participant("e9654321"),
                            new Participant("e7307307")))
            ),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a NUS NetId set containing the list of strings given.
     */
    public static Set<Participant> getParticipantSet(String... strings) {
        return Arrays.stream(strings)
                .map(Participant::new)
                .collect(Collectors.toSet());
    }
}
