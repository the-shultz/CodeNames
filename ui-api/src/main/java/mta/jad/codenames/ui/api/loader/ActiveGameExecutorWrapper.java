package mta.jad.codenames.ui.api.loader;

import mta.jad.codenames.ui.api.dto.execution.chat.ChatMessage;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;
import mta.jad.codenames.ui.api.game.ActiveGame;

import java.util.List;
import java.util.function.Consumer;

public class ActiveGameExecutorWrapper extends UIExecutorWrapper implements ActiveGame {

    private final ActiveGame delegate;

    public ActiveGameExecutorWrapper(ActiveGame delegate) {
        super(3);
        this.delegate = delegate;
    }

    @Override
    public void registerWinnerLooserUpdates(WinLooseStatus onWinnerLooserUpdates) {
        delegate.registerWinnerLooserUpdates(onWinnerLooserUpdates);
    }

    @Override
    public void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates) {
        delegate.registerActiveGameUpdates(onActiveGameUpdates);
    }

    @Override
    public void registerChatUpdates(Consumer<List<ChatMessage>> onChatMessagesUpdates) {
        delegate.registerChatUpdates(onChatMessagesUpdates);
    }

    @Override
    public void sendMessage(String message, Runnable onSuccess, Consumer<String> onError) {
        execute(() -> delegate.sendMessage(message, onSuccess, onError));
    }

    @Override
    public void giveDefinition(String definition, int numberOfWords, Runnable onSuccess, Consumer<String> onError) {
        execute(() -> delegate.giveDefinition(definition, numberOfWords, onSuccess, onError));
    }

    @Override
    public void makeGuess(String word, Runnable onSuccess, Consumer<String> onError) {
        execute(() -> delegate.makeGuess(word, onSuccess, onError));
    }

    @Override
    public void endTurn(Runnable onSuccess, Consumer<String> onError) {
        execute(() -> delegate.endTurn(onSuccess, onError));
    }
}
