package mta.jad.codenames.ui.app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import mta.jad.codenames.ui.app.components.TeamCard;

public class DashboardController {

    public HBox hboxTeams;

    private final BooleanProperty isAdmin = new SimpleBooleanProperty(true);

    @FXML
    public void initialize(){
        hboxTeams.getChildren().addAll(new TeamCard(), new TeamCard(), new TeamCard(), new TeamCard());
    }

}
