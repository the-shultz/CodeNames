package mta.jad.codenames.ui.app.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import mta.jad.codenames.ui.app.GameManager;
import mta.jad.codenames.ui.app.data.Color;
import mta.jad.codenames.ui.app.data.Word;

import java.io.IOException;

public class WordCard extends GridPane {

    private final Word word;

    @FXML private ImageView imageRevealed;
    @FXML private Label labelWord;

    private final BooleanProperty revealed = new SimpleBooleanProperty(false);
    private final BooleanProperty clickable = new SimpleBooleanProperty(false);

    public WordCard(Word word){
        this.word = word;
        getStyleClass().add("team-" + Color.neutral);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/component/WordCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setMinSize(GridPane.USE_PREF_SIZE, GridPane.USE_PREF_SIZE);
    }

    @FXML
    public void initialize(){
        this.setOnMouseClicked(event -> {
            if(clickable.get()){
                revealed.set(!revealed.getValue());
            }
        });

        clickable.addListener((observable, oldValue, newValue) -> {
            if(newValue){
                getStyleClass().add("clickable");
            } else {
                getStyleClass().remove("clickable");
            }
        });

        revealed.addListener((observable, oldValue, newValue) -> {
            if(newValue){
                getStyleClass().remove("card");
                getStyleClass().add("card-full");
                getStyleClass().remove("team-"+Color.neutral);
                getStyleClass().add("team-"+word.getColor());
                disableProperty().set(true);
            } else {
                getStyleClass().remove("card-full");
                getStyleClass().add("card");
            }
        });

        imageRevealed.visibleProperty().bind(revealed);
        clickable.bind(GameManager.getInstance().isPlayerTurnProperty());
        labelWord.textProperty().set(word.get());
    }
}
