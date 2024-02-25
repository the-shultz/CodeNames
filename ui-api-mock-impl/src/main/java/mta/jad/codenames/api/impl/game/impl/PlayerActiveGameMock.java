package mta.jad.codenames.api.impl.game.impl;

import mta.jad.codenames.api.impl.game.turns.api.MockedTurn;
import mta.jad.codenames.api.impl.game.turns.impl.MakeAGuessMockTurn;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameTeamStatus;
import mta.jad.codenames.ui.api.game.chat.ChatActions;

import java.util.List;
import java.util.function.Consumer;

public class PlayerActiveGameMock extends AbstractActiveGameMock {

    private final boolean makeDefinitionSuccess;
    private final boolean makeGuessSuccess;
    private final LatchWrapper latch;

    public PlayerActiveGameMock(ChatActions mockChatBehavior, List<MockedTurn> turns, ActiveGameData baseGameDetails, boolean makeDefinitionSuccess, boolean makeGuessSuccess) {
        super(mockChatBehavior, turns, baseGameDetails);
        this.makeDefinitionSuccess = makeDefinitionSuccess;
        this.makeGuessSuccess = makeGuessSuccess;
        latch = new LatchWrapper();

        turns.forEach(turn -> turn.setLatch(latch));
    }

    @Override
    public void giveDefinition(String definition, int numberOfWords, Runnable onSuccess, Consumer<String> onError) {
        if (!makeDefinitionSuccess) {
            onError.accept("Failed to give definition");
            latch.countDown();
            return;
        }

        baseGameDetails.getCurrentTurn().setDefinition(definition);
        baseGameDetails.getCurrentTurn().setNumberOfWordsRemainingToGuess(numberOfWords);
        onSuccess.run();

        latch.countDown();
    }

    @Override
    public void makeGuess(String word, Runnable onSuccess, Consumer<String> onError) {
        if (!makeGuessSuccess) {
            onError.accept("Failed to make guess");
            latch.countDown();
            return;
        }

        new MakeAGuessMockTurn(0, word).perform(baseGameDetails);
        onSuccess.run();

        latch.countDown();
    }

    @Override
    public void endTurn(Runnable onSuccess, Consumer<String> onError) {
        onSuccess.run();
        latch.countDown();
    }

    @Override
    protected void startSequence() {
        // invoke the sequence only if both onActiveGameUpdates and onWinnerLooserUpdates were given and are not null
        if (onActiveGameUpdates == null || onWinnerLooserUpdates == null) {
            return;
        }

        new Thread(() -> {

            // sleep for 3 seconds before starting the sequence
            sleepSomeTime(3000);

            turns.forEach(turn -> {
                turn.perform(baseGameDetails);

                // update the teamStatus map with the new status of the teams.
                // if it was found that a team's status is changed, call the onWinnerLooserUpdates with the current status
                baseGameDetails
                        .getTeams()
                        .forEach(team -> {
                            ActiveGameTeamStatus currentStatus = teamStatus.get(team.getName());
                            ActiveGameTeamStatus newStatus = team.getStatus();
                            if (currentStatus != newStatus) {
                                teamStatus.put(team.getName(), newStatus);
                                onWinnerLooserUpdates.update(team.getName(), newStatus,
                                        newStatus == ActiveGameTeamStatus.WINNER ?
                                                "The team " + team.getName() + " won the game!" :
                                                "The team " + team.getName() + " lost the game!");
                            }
                        });

                onActiveGameUpdates.accept(baseGameDetails);

                latch.await();
            });
        }).start();
    }
}