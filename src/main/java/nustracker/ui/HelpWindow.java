package nustracker.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nustracker.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2122s1-cs2103t-t11-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USER_GUIDE_URL;
    public static final String COPY_LINK_TEXT = "Open User Guide";

    public static final String ADD_COMMAND = "add";
    public static final String ADD_COMMAND_EXAMPLE = ADD_COMMAND
            + " n/STUDENT_NAME m/MAJOR id/STUDENT_ID y/YEAR p/NUMBER e/EMAIL [ev/EVENT]";

    public static final String FILTER_COMMAND = "filter";
    public static final String FILTER_COMMAND_EXAMPLE = FILTER_COMMAND
            + " [id/STUDENT_ID] [ev/EVENT_NAME] [n/STUDENT_NAME]";

    public static final String EDIT_COMMAND = "edit";
    public static final String EDIT_COMMAND_EXAMPLE = EDIT_COMMAND
            + " INDEX [n/NAME] [m/MAJOR] [id/Student ID] [y/YEAR] [p/PHONE] [e/EMAIL] [ev/EVENT]";

    public static final String CREATE_COMMAND = "create";
    public static final String CREATE_COMMAND_EXAMPLE = CREATE_COMMAND;

    public static final String ENROLL_COMMAND = "enroll";
    public static final String ENROLL_COMMAND_EXAMPLE = ENROLL_COMMAND;

    public static final String DELETE_COMMAND = "delete";
    public static final String DELETE_COMMAND_EXAMPLE = DELETE_COMMAND + " INDEX";

    public static final String LIST_COMMAND = "list";
    public static final String LIST_COMMAND_EXAMPLE = LIST_COMMAND;

    public static final String EXIT_COMMAND = "exit";
    public static final String EXIT_COMMAND_EXAMPLE = EXIT_COMMAND;

    public static final String STUDENTS_COMMAND = "students";

    public static final String EVENTS_COMMAND = "events";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private VBox helpWindow;

    @FXML
    private Text helpMessage;

    @FXML
    private Text addCommandExample;

    @FXML
    private Text listCommandExample;

    @FXML
    private Text filterCommandExample;

    @FXML
    private Text editCommandExample;

    @FXML
    private Text deleteCommandExample;

    @FXML
    private Text exitCommandExample;

    @FXML
    private Text createCommandExample;

    @FXML
    private Text enrollCommandExample;

    @FXML
    private Text studentsCommandExample;

    @FXML
    private Text eventsCommandExample;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);

        helpMessage.setText(HELP_MESSAGE);
        addCommandExample.setText(ADD_COMMAND_EXAMPLE);
        filterCommandExample.setText(FILTER_COMMAND_EXAMPLE);
        editCommandExample.setText(EDIT_COMMAND_EXAMPLE);
        deleteCommandExample.setText(DELETE_COMMAND_EXAMPLE);
        listCommandExample.setText(LIST_COMMAND_EXAMPLE);
        exitCommandExample.setText(EXIT_COMMAND_EXAMPLE);
        createCommandExample.setText(CREATE_COMMAND_EXAMPLE);
        enrollCommandExample.setText(ENROLL_COMMAND_EXAMPLE);
        studentsCommandExample.setText(STUDENTS_COMMAND);
        eventsCommandExample.setText(EVENTS_COMMAND);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void openUserGuide() {
        Desktop desktop = java.awt.Desktop.getDesktop();

        try {
            URI url = new URI(USER_GUIDE_URL);
            desktop.browse(url);
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.INFO, "The user guide could not be opened!");
        }
    }

    /**
     * Copies the AddCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyAddCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(ADD_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the ListCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyListCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(LIST_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the FilterCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyFilterCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(FILTER_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the EditCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyEditCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(EDIT_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the DeleteCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyDeleteCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(DELETE_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the ExitCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyExitCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(EXIT_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the CreateCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyCreateCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(CREATE_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the EnrollCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyEnrollCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(ENROLL_COMMAND_EXAMPLE);
        clipboard.setContent(url);
    }

    /**
     * Copies the StudentsCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyStudentsCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(STUDENTS_COMMAND);
        clipboard.setContent(url);
    }

    /**
     * Copies the EventsCommand to the user guide to the clipboard.
     */
    @FXML
    private void copyEventsCommand() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(EVENTS_COMMAND);
        clipboard.setContent(url);
    }
}
