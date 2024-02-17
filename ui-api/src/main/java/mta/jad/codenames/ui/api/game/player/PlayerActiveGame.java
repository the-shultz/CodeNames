package mta.jad.codenames.ui.api.game.player;

import java.util.function.Consumer;

public interface PlayerActiveGame {

    /**
     * Gives a definition for a word. the definition is composed of a phrase and the number of words it represents.
     * it is the responsibility of the implementation to know which team this definition belongs to
     * @param definition the phrase of the definition
     * @param numberOfWords the number of words the definition represents
     * @param onSuccess a callback to be called when the definition is given successfully.
     * @param onError a callback to be called when the definition failed to be given due to an exception or an unplanned error.
     */
    void giveDefinition(String definition, int numberOfWords, Runnable onSuccess, Consumer<String> onError);

    /**
     * Makes a guess for a word.
     * it is the responsibility of the implementation to know which team this guess belongs to
     * @param word the word to guess
     * @param onSuccess a callback to be called when the guess is made successfully.
     * @param onError a callback to be called when the guess failed to be made due to an exception or an unplanned error.
     */
    void makeGuess(String word, Runnable onSuccess, Consumer<String> onError);

    /**
     * Ends the turn of the current team
     * it is the responsibility of the implementation to know which team this turn belongs to
     * @param onSuccess a callback to be called when the turn is ended successfully.
     * @param onError a callback to be called when the turn failed to be ended due to an exception or an unplanned error.
     */
    void endTurn(Runnable onSuccess, Consumer<String> onError);
}
