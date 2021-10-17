package nustracker.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nustracker.commons.core.LogsCenter;

public class SettingsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(SettingsWindow.class);
    private static final String FXML = "SettingsWindow.fxml";

    @FXML
    private VBox container;
    @FXML
    private ColorPicker glowColor;
    @FXML
    private Button doneButton;

    public SettingsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new SettingsWindow.
     */
    public SettingsWindow() {
        this(new Stage());
    }

    /**
     * Sets the current previously saved glow color (or default one if not saved previously)
     *
     * @param colorHexCode The hex code of the glow color
     */
    public void setCurrentColor(String colorHexCode) {
        Color currentColour = Color.web(colorHexCode);
        glowColor.setValue(currentColour);
    }

    /**
     * Getter method, but formats the color from the color selector in a string
     * hex format e.g #FFFFFF
     *
     * @return String color hex code
     */
    public String getGlowHexCode() {
        //Current color in the color picker
        Color currColor = glowColor.getValue();

        int redValue = (int) (currColor.getRed() * 255);
        int greenValue = (int) (currColor.getGreen() * 255);
        int blueValue = (int) (currColor.getBlue() * 255);

        return String.format("#%02X%02X%02X", redValue, greenValue, blueValue);
    }

    /**
     * Shows the settings window
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing settings of the application");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns ture if the help window is currently being seen
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the Settings window
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the Settings window
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
