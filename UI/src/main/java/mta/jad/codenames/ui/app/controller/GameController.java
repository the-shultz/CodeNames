package mta.jad.codenames.ui.app.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mta.jad.codenames.ui.app.components.GameBoard;
import mta.jad.codenames.ui.app.components.chat.ChatBox;
import mta.jad.codenames.ui.app.data.Color;
import mta.jad.codenames.ui.app.data.Message;
import mta.jad.codenames.ui.app.data.Role;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameController {

    private final BooleanProperty guesser = new SimpleBooleanProperty(false);

    public ComboBox<Integer> comboBoxWordCount;
    public GameBoard gameBoard;
    public ChatBox chatBox;
    public TextField textFieldClue;
    public Button buttonGameAction;

    @FXML
    public void initialize(){
        buttonGameAction.textProperty().bind(Bindings.when(guesser)
                .then("END")
                .otherwise("SUBMIT"));
        comboBoxWordCount.disableProperty().bind(guesser);
        textFieldClue.disableProperty().bind(guesser);
    }

    public void setup(){
        ObservableList<Integer> numbers = IntStream.rangeClosed(0, 25)//TODO make it the number of words
                .boxed()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        comboBoxWordCount.setItems(numbers);

        chatBox.addMessage(new Message("Sapir", Role.Spymaster, Color.blue, "Hello"));
        chatBox.addMessage(new Message("Aviad", Role.Guesser, Color.blue, "Hello"));
        chatBox.addMessage(new Message("Sapir", Role.Spymaster, Color.blue, "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest"));
        chatBox.addMessage(new Message("Sapir red", Role.Spymaster, Color.red, "test"));
        chatBox.addMessage(new Message("Sapir red", Role.Spymaster, Color.red, "test2"));
    }
}
