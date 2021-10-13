package nustracker.model.student;

import java.util.function.Predicate;

import nustracker.model.event.EventName;


/**
 * Tests that a {@code Student}'s {@code EnrolledEvents} matches the keyword given.
 */
public class EnrolledEventsContainsKeywordsPredicate implements Predicate<Student> {
    private final String keyword;

    /**
     * Constructs an {@code EnrolledEventsContainsKeywordsPredicate}.
     *
     * @param keyword A valid event name.
     */
    public EnrolledEventsContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return student.getEvents().isEnrolledInEvent(new EventName(keyword));
    }

    /**
     * Returns the keyword.
     *
     * @return the keyword.
     */
    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnrolledEventsContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((EnrolledEventsContainsKeywordsPredicate) other).keyword)); // state check
    }

}
