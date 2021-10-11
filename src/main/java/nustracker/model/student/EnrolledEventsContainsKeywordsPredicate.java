package nustracker.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code EnrolledEvents} matches the keyword given.
 */
public class EnrolledEventsContainsKeywordsPredicate implements Predicate<Student> {
    private final String keyword;

    public EnrolledEventsContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        //fix after enrolled events is implemented

        //return keyword.stream()
        //      .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getNusNetId().value, keyword));
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnrolledEventsContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((EnrolledEventsContainsKeywordsPredicate) other).keyword)); // state check
    }

}
