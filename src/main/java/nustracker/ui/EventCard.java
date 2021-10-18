package nustracker.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import nustracker.model.event.Event;

/**
 * A UI component that displays information of an {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Text name;
    @FXML
    private Text date;
    @FXML
    private Text time;
    @FXML
    private FlowPane participants;
    @FXML
    private FlowPane blacklist;

    /**
     * Creates an {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        name.setText(event.getName().getEventName());
        date.setText(event.getDate().toString());
        time.setText(event.getTime().toString());

        event.getParticipants().stream()
                .sorted(Comparator.comparing(participant -> participant.nusNetId))
                .forEach(participant -> participants.getChildren().add(
                        new Label(participant.nusNetId)));

        event.getBlacklist().stream()
                .sorted(Comparator.comparing(blacklisted -> blacklisted.nusNetId))
                .forEach(blacklisted -> blacklist.getChildren().add(
                        new Label(blacklisted.nusNetId)));

        //Ensures that the participants and blacklist will be evenly spaced out
        participants.setVgap(4.0);
        participants.setHgap(4.0);
        blacklist.setVgap(4.0);
        blacklist.setHgap(4.0);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return event.equals(card.event);
    }
}
