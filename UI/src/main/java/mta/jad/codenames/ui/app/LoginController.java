package mta.jad.codenames.ui.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import mta.jad.codenames.ui.app.components.WordCard;
import mta.jad.codenames.ui.app.style.StyleManager;

import java.io.IOException;
import java.net.URL;

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
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/form/DashboardForm.fxml"));
                    Parent root = loader.load();

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
                    e.printStackTrace();
                }

            }
        });
    }
}
