package mta.jad.codenames.ui.app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameManager {

    private static GameManager instance;
    private GameManager() {}
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private final BooleanProperty isPlayerTurn = new SimpleBooleanProperty(true);

    public void toggleTurn() {
        isPlayerTurn.set(!isPlayerTurn.get());
    }

    public BooleanProperty isPlayerTurnProperty() {
        return isPlayerTurn;
    }

}
