package nustracker.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import nustracker.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Text name;
    @FXML
    private Text phone;
    @FXML
    private Text email;
    @FXML
    private Text year;
    @FXML
    private Text major;
    @FXML
    private Text studentId;
    @FXML
    private Text enrolledEvents;


    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);

        this.student = student;
        name.setText(student.getName().fullName);

        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        year.setText(student.getYear().value);
        major.setText(student.getMajor().value);
        studentId.setText(student.getStudentId().value);
        enrolledEvents.setText(student.getEvents().getEventNamesString());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return student.equals(card.student);
    }
}
