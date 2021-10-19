package nustracker.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nustracker.commons.core.GuiSettings;
import nustracker.commons.core.LogsCenter;

public class SettingsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(SettingsWindow.class);
    private static final String FXML = "SettingsWindow.fxml";

    @FXML
    private VBox container;
    @FXML
    private ColorPicker glowColorPicker;
    @FXML
    private Button doneButton;

    private Color glowColor;
    private StudentListPanel studentListPanel;

    private SettingsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new SettingsWindow.
     */
    public SettingsWindow(GuiSettings guiSettings) {
        this(new Stage());

        this.studentListPanel = studentListPanel;

        glowColorPicker.setOnAction(e -> {
            updateColor(getGlowHexCode());
        });

        setCurrentColor(guiSettings.getGlowHexCode());
    }

    public void setStudentListPanel(StudentListPanel studentListPanel) throws IllegalArgumentException {
        if (studentListPanel == null) {
            logger.log(Level.INFO, "studentListPanel cannot be null!");
            throw new IllegalArgumentException("studentListPanel cannot be null!");
        }
        this.studentListPanel = studentListPanel;
    }

    private void updateColor(String color) {
        assert studentListPanel != null;
        studentListPanel.updateGlow(color);
    }

    /**
     * Sets the previously saved glow color as the current color in
     * the color picker.
     *
     * @param colorHexCode The hex code of the glow color
     */
    private void setCurrentColor(String colorHexCode) {
        Color currentColour = Color.web(colorHexCode);
        glowColorPicker.setValue(currentColour);
    }

    public ColorPicker getGlowColorPicker() {
        assert glowColorPicker != null;
        return glowColorPicker;
    }

    /**
     * Getter method, but formats the color from the color selector in a string
     * hex format e.g #FFFFFF
     *
     * @return String color hex code
     */
    public String getGlowHexCode() {
        //Current color in the color picker
        Color currColor = glowColorPicker.getValue();

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
