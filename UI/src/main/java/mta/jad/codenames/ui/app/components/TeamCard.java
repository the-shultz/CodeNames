package mta.jad.codenames.ui.app.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TeamCard extends GridPane {

    public DataLabel dataLabelTeam;
    public DataLabel dataLabelCards;
    public CapacityLabel capacitySpymasters;
    public VBox vboxSpymasters;
    public Button buttonSpymastersJoin;
    public CapacityLabel capacityGuessers;
    public VBox vboxGuessers;
    public Button buttonGuessersJoin;

    private final BooleanProperty joinEnabled = new SimpleBooleanProperty(true);

    public TeamCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/component/TeamCard.fxml"));
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
        buttonGuessersJoin.visibleProperty().bindBidirectional(buttonGuessersJoin.managedProperty());
        buttonSpymastersJoin.visibleProperty().bindBidirectional(buttonSpymastersJoin.managedProperty());

        buttonGuessersJoin.visibleProperty().bind(joinEnabled.and(capacityGuessers.fullProperty().not()));
        buttonSpymastersJoin.visibleProperty().bind(joinEnabled.and(capacitySpymasters.fullProperty().not()));
    }
}
