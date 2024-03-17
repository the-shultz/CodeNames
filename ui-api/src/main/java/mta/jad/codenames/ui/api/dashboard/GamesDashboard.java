package mta.jad.codenames.ui.api.dashboard;

import mta.jad.codenames.ui.api.dashboard.admin.AdminGamesDashboard;
import mta.jad.codenames.ui.api.dashboard.player.PlayerGamesDashboard;
import mta.jad.codenames.ui.api.dto.definition.FullGameDetails;
import mta.jad.codenames.ui.api.dto.definition.LightweightGameDetails;

import java.util.List;
import java.util.function.Consumer;

public interface GamesDashboard extends AdminGamesDashboard, PlayerGamesDashboard {

    /**
     * REACTIVE METHOD
     * Register a callback to be called whenever the list of available games has changed.
     * The UI will delete existing list of shown games and will render these list according to their order.
     * If a certain game was already selected beforehand, and if it exists in this new list - it will be re-selected again, which will follow triggering the getGameDetails method.
     * beware of calling this method when not needed. calling it too frequently might cause performance issues and bad user experience (flickering ui; loosing selection context etc)
     * this method MUST return immediately, and the callback should be called on a different thread.
     * @param onGameDetailsChanged the callback to be called when the game details are changed
     */
    void registerLightweightGameDetailsCallback(Consumer<List<LightweightGameDetails>> onGameDetailsChanged);

    /**
     * ACTIVE METHOD
     * Get details of a specific game from the server.
     * This method will be called when the user selects a game from the list of available games.
     * @param gameName the name of the game to fetch its details
     * @param gameDetails the callback to be called when the game details are retrieved successfully.
     * @param fullGameDetailsFailure the callback to be called when the game details failed to be retrieved due to an exception or an unplanned error.
     *                              The value will be an informative error message that will be displayed to the user.
     */
    void getGameDetails(String gameName, Consumer<FullGameDetails> gameDetails, Consumer<String> fullGameDetailsFailure);
}
