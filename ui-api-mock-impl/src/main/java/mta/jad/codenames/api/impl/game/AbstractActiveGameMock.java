package mta.jad.codenames.api.impl.game;

import mta.jad.codenames.ui.api.dto.execution.chat.ChatMessage;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;
import mta.jad.codenames.ui.api.game.ActiveGame;
import mta.jad.codenames.ui.api.game.chat.ChatActions;

import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractActiveGameMock implements ActiveGame {

    private final ChatActions mockChatBehavior;

    public AbstractActiveGameMock(ChatActions mockChatBehavior) {
        this.mockChatBehavior = mockChatBehavior;
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
    public void registerWinnerLooserUpdates(WinLooseStatus onWinnerLooserUpdates) {

    }

    @Override
    public void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates) {

    }

    @Override
    public void giveDefinition(String definition, int numberOfWords, Runnable onSuccess, Consumer<String> onError) {

    }

    @Override
    public void makeGuess(String word, Runnable onSuccess, Consumer<String> onError) {

    }

    @Override
    public void endTurn(Runnable onSuccess, Consumer<String> onError) {

    }
}
