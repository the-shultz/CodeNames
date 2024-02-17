package mta.jad.codenames.ui.api.game.admin;

import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

import java.util.function.Consumer;

public interface AdminActiveGame {

    /**
     * Register for active game updates to be given to the admin each time the game state changes.
     * Upon each update the data in the ActiveGameData instance will be updated in the UI.
     * @param onActiveGameUpdates the consumer to be called with the updated game state
     */
    void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates);
}
