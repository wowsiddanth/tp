package nustracker.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import nustracker.testutil.TypicalStudents;

public class StudentCardTest {

    @Test
    public void correctGlowColor_greenHexCode_greenGlow() {
        Circle testCircle = new Circle();
        Color green = Color.web("#00FF00");
        StudentCard studentCard = new StudentCard(TypicalStudents.ALICE, "#00FF00");

        studentCard.setGlow(testCircle, "#00FF00");
        DropShadow dropShadow = (DropShadow) testCircle.getEffect();
        assertEquals(green, dropShadow.getColor());
    }

}
