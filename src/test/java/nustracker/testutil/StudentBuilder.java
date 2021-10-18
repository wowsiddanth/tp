package nustracker.testutil;

import java.util.HashSet;
import java.util.Set;

import nustracker.model.event.Event;
import nustracker.model.student.Email;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.NusNetId;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.Year;
import nustracker.model.tag.Tag;
import nustracker.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_YEAR = "2";
    public static final String DEFAULT_MAJOR = "CS";
    public static final String DEFAULT_NUSNETID = "e1234567";

    private Name name;
    private Phone phone;
    private Email email;
    private Year year;
    private Major major;
    private NusNetId nusNetId;
    private Set<Tag> tags;
    private EnrolledEvents enrolledEvents;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        year = new Year(DEFAULT_YEAR);
        major = new Major(DEFAULT_MAJOR);
        nusNetId = new NusNetId(DEFAULT_NUSNETID);
        tags = new HashSet<>();
        enrolledEvents = new EnrolledEvents();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        year = studentToCopy.getYear();
        major = studentToCopy.getMajor();
        nusNetId = studentToCopy.getNusNetId();
        tags = new HashSet<>(studentToCopy.getTags());
        enrolledEvents = studentToCopy.getEvents();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code Student} that we are building.
     */
    public StudentBuilder withYear(String year) {
        this.year = new Year(year);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Student} that we are building.
     */
    public StudentBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code NusNetId} of the {@code Student} that we are building.
     */
    public StudentBuilder withNusNetId(String nusNetId) {
        this.nusNetId = new NusNetId(nusNetId);
        return this;
    }

    /**
     * Sets the {@code EnrolledEvents} of the {@code Student} that we are building to contain {@code event}.
     */
    public StudentBuilder withEvent(Event event) {
        this.enrolledEvents = this.enrolledEvents.enrollIntoEvent(event);
        return this;
    }

    /**
     * Sets the {@code EnrolledEvents} of the {@code Student} that we are building
     * to the provided {@code enrolledEvents}.
     */
    public StudentBuilder withEvents(EnrolledEvents enrolledEvents) {
        this.enrolledEvents = enrolledEvents;
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, year, major, nusNetId, tags, enrolledEvents);
    }

}
