package nustracker.ui;

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

import java.io.File;
import java.util.Objects;

import static java.awt.Image.SCALE_DEFAULT;

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
    private Text nusNetId;
    @FXML
    private Text enrolledEvents;
    @FXML
    private Circle profilePicture;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;

        setStudentFields();
        setProfilePicture();
    }

    public void setStudentFields() {
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        year.setText(student.getYear().value);
        major.setText(student.getMajor().value);
        nusNetId.setText(student.getNusNetId().value);
        enrolledEvents.setText(student.getEvents().getEventNamesString());
    }



    public void setProfilePicture() {

        String newStudentName = replaceWhiteSpacesWithUnderscore(student.getName().fullName);

        Image image = new Image(Objects.requireNonNull(this.getClass()
                .getResourceAsStream("/images/default.png")));

        String pathOfProfilePictureJpg = "src/main/resources/images/" + newStudentName + ".jpg";
        String pathOfProfilePicturePng = "src/main/resources/images/" + newStudentName + ".png";

        File jpg = new File(pathOfProfilePictureJpg);
        File png = new File(pathOfProfilePicturePng);

        if (jpg.isFile()) {
            image = new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/images/" + newStudentName + ".jpg")), 400, 400, true, true);
        } else if (png.isFile()) {
            image = new Image(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/images/" + newStudentName + ".png")), 400, 400, true,  true);
        }


        //Setting glow around picture
        profilePicture.setRadius(60);
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.web("E9AFFF"));
        borderGlow.setWidth(100);
        borderGlow.setHeight(100);
        profilePicture.setEffect(borderGlow);
        profilePicture.setFill(new ImagePattern(image));

        profilePicture.setFocusTraversable(true);
    }

    public String replaceWhiteSpacesWithUnderscore(String studentName) {
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
