package mta.jad.codenames.ui.api.dashboard.admin;

import java.util.function.Consumer;

public interface AdminGamesDashboard {

    /**
     * Load a new game to the server
     * upon loading the server should validate if this game is valid and return a result in accordance
     * @param gameXmlPath the path to the game xml file
     * @param dictionaryPath the path to the dictionary file
     * @param onGameLoaded the callback to be called when the game is loaded successfully to the server
     * @param onGameLoadFailure the callback to be called when the game failed to load to the server,
     *                          either due to an exception or an unplanned error, or due to logical failure.
     *                          The value will be an informative error message that will be displayed to the user.
     */
    void loadNewGame(String gameXmlPath, String dictionaryPath, Runnable onGameLoaded, Consumer<String> onGameLoadFailure);

}
