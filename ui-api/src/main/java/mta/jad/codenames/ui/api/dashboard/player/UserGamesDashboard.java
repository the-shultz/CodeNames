package mta.jad.codenames.ui.api.dashboard.player;

import java.util.function.Consumer;

public interface UserGamesDashboard {

    /**
     * Registers a player as a definer in a game.
     * @param gameName the name of the game
     * @param teamName the name of the team
     * @param playerName the name of the player
     * @param onRegistrationSuccess a callback to be called when the registration is successful.
     * @param onRegistrationFailure a callback to be called when the registration fails due to an exception or an unplanned error.
*    *                              The value will be an informative error message that will be displayed to the user.
     */
    void registerDefiner(String gameName, String teamName, String playerName, Runnable onRegistrationSuccess, Consumer<String> onRegistrationFailure);

    /**
     * Registers a player as a guesser in a game.
     * @param gameName the name of the game
     * @param teamName the name of the team
     * @param playerName the name of the player
     * @param onRegistrationSuccess a callback to be called when the registration is successful.
     * @param onRegistrationFailure a callback to be called when the registration fails due to an exception or an unplanned error.
     *    *                              The value will be an informative error message that will be displayed to the user.
     */
    void registerGuesser(String gameName, String teamName, String playerName, Runnable onRegistrationSuccess, Consumer<String> onRegistrationFailure);
}
