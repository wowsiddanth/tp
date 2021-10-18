package nustracker.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code NusNetId} matches any of the keywords given.
 */
public class NusNetIdContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    /**
     * Constructs an {@code NusNetIdContainsKeywordsPredicate}.
     *
     * @param keywords A list of NUS NetIDs as keywords.
     */
    public NusNetIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> student.getNusNetId().value.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NusNetIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NusNetIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
