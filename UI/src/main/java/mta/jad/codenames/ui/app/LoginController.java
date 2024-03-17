package mta.jad.codenames.ui.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import mta.jad.codenames.ui.api.access.CodeNamesUIApi;
import mta.jad.codenames.ui.app.style.StyleManager;

import java.io.IOException;

public class LoginController {


    @FXML public Button buttonLogin;
    @FXML public RadioButton radioAdmin;
    @FXML public RadioButton radioUser;
    @FXML public ToggleGroup toggleGroupType;
    @FXML public TextField textFieldName;
    @FXML public GridPane gridLayout;

    @FXML
    public void initialize() {
        textFieldName.disableProperty().bind(radioUser.selectedProperty().not());

        buttonLogin.setOnAction(event -> {

            if (radioAdmin.isSelected()) {
                CodeNamesUIApi.INSTANCE.getLogin().adminLogin(createOnSuccessfulLoginContinuation(event, true), errorMessage -> {
                    System.out.println("Failure upon admin login: " + errorMessage);
                    // setup alert box. should be global to whole UI
                });
            } else {
                CodeNamesUIApi.INSTANCE.getLogin().playerLogin(textFieldName.getText(), createOnSuccessfulLoginContinuation(event, false), errorMessage -> {
                    System.out.println("Failure upon player login: " + errorMessage);
                    // setup alert box. should be global to whole UI
                });
            }
        });
    }

    private Runnable createOnSuccessfulLoginContinuation(ActionEvent event, boolean isAdmin) {
        return () -> {
            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/form/DashboardForm.fxml"));
                    Parent root = loader.load();
                    DashboardController dashboardController = loader.getController();
                    dashboardController.setAdminMode(isAdmin);

                    // Create a new stage (window)
                    Stage newStage = new Stage();
                    newStage.setTitle("Dashboard");
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
            });
        };
    }
}