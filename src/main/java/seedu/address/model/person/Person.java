package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Year year;
    private final Major major;
    private final NusNetId nusNetId;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Year year, Major major, NusNetId nusNetId, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, year, major, nusNetId, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.year = year;
        this.major = major;
        this.nusNetId = nusNetId;
        this.tags.addAll(tags);
        Major.addStudent(this);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Year getYear() {
        return year;
    }

    public Major getMajor() {
        return major;
    }

    public NusNetId getNusNetId() {
        return nusNetId;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if the other person has the same credentials in the fields
     * where having the same ones is not allowed like the NUS NetId, email, & phone.
     */
    public boolean hasDuplicateCredentials(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        boolean notNull = otherPerson != null;
        boolean sameId = notNull && otherPerson.getNusNetId().equals(getNusNetId());
        boolean sameEmail = notNull && otherPerson.getEmail().equals(getEmail());
        boolean samePhone = notNull && otherPerson.getPhone().equals(getPhone());

        return sameId || sameEmail || samePhone;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getYear().equals(getYear())
                && otherPerson.getMajor().equals(getMajor())
                && otherPerson.getNusNetId().equals(getNusNetId())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, year, major, nusNetId, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Year: ")
                .append("; Major: ")
                .append(getMajor())
                .append(getYear())
                .append("; NUSNetId: ")
                .append(getNusNetId());
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
