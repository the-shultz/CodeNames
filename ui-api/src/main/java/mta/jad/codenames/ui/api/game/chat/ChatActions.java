package mta.jad.codenames.ui.api.game.chat;

import mta.jad.codenames.ui.api.dto.execution.chat.ChatMessage;

import java.util.List;
import java.util.function.Consumer;

public interface ChatActions {

    /**
     * REACTIVE METHOD
     * Register a consumer to be called when the chat messages are updated.
     * The UI will take the list of ChatMessage and will append them to the chat area, so this consumer should be called
     * with the delta of new chat messages only (and not with all of them, all the time).
     * it is the responsibility of the caller to send the correct chat messages according to the relevant type of the player
     * (e.g. the guessers won't get the definer's chat messages; definers and admin will get all messages).
     * this method MUST return immediately, and the callback should be called on a different thread.
     * @param onChatMessagesUpdates the consumer to be called when the chat messages are updated.
     */
    void registerChatUpdates(Consumer<List<ChatMessage>> onChatMessagesUpdates);

    /**
     * ACTIVE METHOD
     * enables a user to send a new message to the chat
     * it is the responsibility of the implementation to know in ahead which type of player is sending the message.
     * @param message the message to send
     * @param onSuccess a callback to be called when the message is sent successfully.
     * @param onError a callback to be called when the message failed to be sent due to an exception or an unplanned error.
     */
    void sendMessage(String message, Runnable onSuccess, Consumer<String> onError);
}
