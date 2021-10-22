package nustracker.ui;

import java.io.File;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    @FXML
    private Circle profilePicture;

    //Represents the default profile picture for the student to be used
    private Image studentImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/default.png")));

    /**
     * Creates a {@code StudentCode} with the given {@code Student}
     */
    public StudentCard(Student student) {
        super(FXML);
        this.student = student;

        setStudentFields();
        setProfilePicture();
    }

    /**
     * Sets the student's fields to be displayed on the student card
     */
    private void setStudentFields() {
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        year.setText(student.getYear().value);
        major.setText(student.getMajor().value);
        studentId.setText(student.getStudentId().value);
        enrolledEvents.setText(student.getEvents().getEventNamesString());
    }

    /**
     * Sets the profile picture's glow
     */
    public void setGlow(String hexCode) {
        profilePicture.setRadius(60);

        profilePicture.setStroke(Color.web(hexCode));
        profilePicture.setStrokeWidth(4);

        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.web(hexCode));
        borderGlow.setWidth(100);
        borderGlow.setHeight(100);

        profilePicture.setEffect(borderGlow);
    }

    /**
     * Selects the student image to be used as the profile picture.
     * It checks to see if a profile picture with the student's name exists within the
     * images folder. If it exists, reassigns image. If not, the image remains as the default image.
     * Accepts both .png and .jpg file formats.
     */
    private void selectStudentImage() {
        String newStudentName = matchImageName(student.getName().fullName);

        String pathOfProfilePictureJpg = "src/main/resources/images/" + newStudentName + ".jpg";
        String pathOfProfilePicturePng = "src/main/resources/images/" + newStudentName + ".png";

        File jpg = new File(pathOfProfilePictureJpg);
        File png = new File(pathOfProfilePicturePng);

        //Checks if an image with the student's name exists
        if (jpg.isFile()) {
            studentImage = new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/images/" + newStudentName + ".jpg")), 400,
                    400, true, true);
        } else if (png.isFile()) {
            studentImage = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/" + newStudentName + ".png")), 400,
                   400, true, true);
        }
    }

    /**
     * Sets the profile picture with the student image
     */
    private void setProfilePicture() {
        selectStudentImage();
        profilePicture.setFill(new ImagePattern(studentImage));
    }

    /**
     * Replaces student's full name to match student image's name (if valid).
     * For example, if the student's name is John Doe, it changes it to
     * john-doe.
     *
     * @param studentName The student name to be changed
     * @return Student name but whitespaces are replaced with dashes and is in lowercase
     */
    private String matchImageName(String studentName) {
        return studentName.toLowerCase().replace(' ', '-');
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
