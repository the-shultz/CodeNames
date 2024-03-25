package mta.jad.codenames.ui.app.components.chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import mta.jad.codenames.ui.app.data.Message;

import java.io.IOException;

public class ChatBox extends GridPane {

    public VBox vboxMessages;
    public TextField textFieldInput;
    public Button buttonSend;

    public ChatBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/component/chat/ChatBox.fxml"));
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

    public void addMessage(Message message){
        MessageCard messageCard = new MessageCard();
        messageCard.setMessage(message);
        messageCard.prefWidthProperty().bind(vboxMessages.widthProperty());
        vboxMessages.getChildren().add(messageCard);
    }
}
