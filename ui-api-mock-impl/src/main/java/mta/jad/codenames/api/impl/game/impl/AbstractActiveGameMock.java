package mta.jad.codenames.api.impl.game.impl;

import mta.jad.codenames.api.impl.game.turns.api.MockedTurn;
import mta.jad.codenames.ui.api.dto.execution.chat.ChatMessage;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameTeamDetails;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameTeamStatus;
import mta.jad.codenames.ui.api.game.ActiveGame;
import mta.jad.codenames.ui.api.game.chat.ChatActions;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractActiveGameMock implements ActiveGame {

    protected final List<MockedTurn> turns;
    protected final ActiveGameData baseGameDetails;
    private final ChatActions mockChatBehavior;
    protected Consumer<ActiveGameData> onActiveGameUpdates;
    protected WinLooseStatus onWinnerLooserUpdates;
    protected final Map<String, ActiveGameTeamStatus> teamStatus;

    public AbstractActiveGameMock(ChatActions mockChatBehavior, List<MockedTurn> turns, ActiveGameData baseGameDetails) {
        this.mockChatBehavior = mockChatBehavior;
        this.turns = turns;
        this.baseGameDetails = baseGameDetails;

        //traverse all teams of the game and init the teamStatus map with their initial status ActiveGameTeamStatus.IN_GAME
        teamStatus =
                baseGameDetails
                        .getTeams()
                        .stream()
                        .collect(Collectors.toMap(ActiveGameTeamDetails::getName, team -> ActiveGameTeamStatus.IN_GAME));

    }

    @Override
    public void registerChatUpdates(Consumer<List<ChatMessage>> onChatMessagesUpdates) {
        mockChatBehavior.registerChatUpdates(onChatMessagesUpdates);
    }

    @Override
    public void sendMessage(String message, Runnable onSuccess, Consumer<String> onError) {
        mockChatBehavior.sendMessage(message, onSuccess, onError);
    }

    @Override
    public void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates) {
        this.onActiveGameUpdates = onActiveGameUpdates;
        startSequence();
    }

    @Override
    public void registerWinnerLooserUpdates(WinLooseStatus onWinnerLooserUpdates) {
        this.onWinnerLooserUpdates = onWinnerLooserUpdates;
        startSequence();
    }

    @Override
    public void giveDefinition(String definition, int numberOfWords, Runnable onSuccess, Consumer<String> onError) {
        throw new UnsupportedOperationException("This method is not supported in this context");
    }

    @Override
    public void makeGuess(String word, Runnable onSuccess, Consumer<String> onError) {
        throw new UnsupportedOperationException("This method is not supported in this context");
    }

    @Override
    public void endTurn(Runnable onSuccess, Consumer<String> onError) {
        throw new UnsupportedOperationException("This method is not supported in this context");
    }

    protected abstract void startSequence();

    protected void sleepSomeTime(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
