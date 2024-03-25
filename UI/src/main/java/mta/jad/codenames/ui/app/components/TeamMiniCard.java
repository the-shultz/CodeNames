package mta.jad.codenames.ui.app.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TeamMiniCard extends GridPane {

    public DataLabel dataLabelTeam;
    public CapacityLabel capacityScore;

    private final BooleanProperty active = new SimpleBooleanProperty(false);

    public TeamMiniCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/component/TeamMiniCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize(){

    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}