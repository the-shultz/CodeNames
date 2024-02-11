package mta.jad.codenames.ui.api.dashboard.player;

import mta.jad.codenames.ui.api.dto.FullGameDetails;

import java.util.function.Consumer;

public interface UserGamesDashboard {

    /**
     * Registers a player as a definer in a game.
     * @param gameName the name of the game
     * @param teamName the name of the team
     * @param playerName the name of the player
     * @param onRegistrationSuccess a callback to be called when the registration is successful. the entire updated game details needs to be passed to this callback, reflecting the registration of the player.
     * @param onRegistrationFailure a callback to be called when the registration fails due to an exception or an unplanned error.
*    *                              The value will be an informative error message that will be displayed to the user.
     */
    void registerDefiner(String gameName, String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure);

    /**
     * Registers a player as a guesser in a game.
     * @param gameName the name of the game
     * @param teamName the name of the team
     * @param playerName the name of the player
     * @param onRegistrationSuccess a callback to be called when the registration is successful. the entire updated game details needs to be passed to this callback, reflecting the registration of the player.
     * @param onRegistrationFailure a callback to be called when the registration fails due to an exception or an unplanned error.
     *    *                              The value will be an informative error message that will be displayed to the user.
     */
    void registerGuesser(String gameName, String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure);
}
