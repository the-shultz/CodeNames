package mta.jad.codenames.ui.app;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import mta.jad.codenames.ui.api.access.CodeNamesUIApi;
import mta.jad.codenames.ui.app.components.TeamCard;

public class DashboardController {

    public HBox hboxTeams;
    public Button buttonWatch;

    private final BooleanProperty adminMode = new SimpleBooleanProperty();

    @FXML
    public void initialize(){
        buttonWatch.visibleProperty().bind(adminMode);

        //TODO: Testing, remove later
        hboxTeams.getChildren().addAll(new TeamCard(), new TeamCard(), new TeamCard(), new TeamCard());

        //TODO: update with respond to games data
        CodeNamesUIApi.INSTANCE.getGamesDashboard().registerLightweightGameDetailsCallback(gameDetails ->
                Platform.runLater(() -> {
                    System.out.println("gameDetails = " + gameDetails);
                }
        ));
    }

    public void setAdminMode(boolean adminMode) {
        this.adminMode.set(adminMode);
    }
}
