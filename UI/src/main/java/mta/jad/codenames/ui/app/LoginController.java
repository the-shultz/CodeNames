package mta.jad.codenames.ui.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
                URL resource = getClass().getResource("/app/form/DashboardForm.fxml");
                assert resource != null;

                Parent root = null;
                try {
                    root = FXMLLoader.load(resource);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                StyleManager.getInstance().register(scene);
                primaryStage.setScene(scene);
                primaryStage.sizeToScene();
                primaryStage.show();
                primaryStage.setMinWidth(primaryStage.getWidth());
                primaryStage.setMinHeight(primaryStage.getHeight());
            }
        });
    }
}
