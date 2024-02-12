package mta.jad.codenames.ui.app.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class WordCard extends GridPane {

    @FXML private ImageView imageRevealed;

    public BooleanProperty revealedProperty = new SimpleBooleanProperty(false);

    public WordCard(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/component/WordCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void initialize(){
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                revealedProperty.set(!revealedProperty.getValue());
            }
        });

        revealedProperty.addListener((observable, oldValue, newValue) -> {
            getStyleClass().clear();
            if(newValue){
                getStyleClass().add("card-full");
                disableProperty().set(true);
            } else {
                getStyleClass().add("card");
            }
        });

        imageRevealed.visibleProperty().bind(revealedProperty);
    }
}
