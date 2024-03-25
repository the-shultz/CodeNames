package mta.jad.codenames.ui.app.components.chat;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mta.jad.codenames.ui.app.data.Message;

import java.io.IOException;

public class MessageCard extends GridPane {

    public Label labelName;
    public Label labelRole;
    public Label labelContent;

    public MessageCard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/component/chat/MessageCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void initialize() {

    }

    public void setMessage(Message message) {
        labelName.setText(message.getFrom());
        labelContent.setText(message.getContent());
        labelRole.setText(message.getRole().toString());
        getStyleClass().add("team-" + message.getTeam());
    }
}
