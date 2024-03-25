package mta.jad.codenames.ui.app.controller;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;
import mta.jad.codenames.ui.api.access.CodeNamesUIApi;
import mta.jad.codenames.ui.app.components.TeamCard;
import mta.jad.codenames.ui.app.style.StyleManager;

import java.io.IOException;

public class DashboardController {

    public HBox hboxTeams;
    public Button buttonWatch;

    private final BooleanProperty adminMode = new SimpleBooleanProperty();

    @FXML
    public void initialize(){
        buttonWatch.visibleProperty().bind(adminMode);
        buttonWatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/form/GameForm.fxml"));
                    Parent root = loader.load();
                    GameController gameController = loader.getController();
                    gameController.setup();

                    // Create a new stage (window)
                    Stage newStage = new Stage();
                    newStage.setTitle("Game");
                    Scene scene = new Scene(root);
                    StyleManager.getInstance().register(scene);
                    newStage.setScene(scene);
                    newStage.sizeToScene();
                    newStage.show();
                    newStage.setMinWidth(newStage.getWidth());
                    newStage.setMinHeight(newStage.getHeight());

                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    // setup alert box. should be global to whole UI
                }
            }
        });

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
