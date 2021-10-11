package nustracker.model.student;

import java.util.List;
import java.util.function.Predicate;

import nustracker.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code NusNetId} matches any of the keywords given.
 */
public class NusNetIdContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public NusNetIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getNusNetId().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NusNetIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NusNetIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
