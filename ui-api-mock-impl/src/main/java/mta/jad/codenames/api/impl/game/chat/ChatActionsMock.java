package mta.jad.codenames.api.impl.game.chat;

import lombok.Builder;
import mta.jad.codenames.api.impl.game.AbstractActiveGameMock;
import mta.jad.codenames.api.impl.util.MockUtils;
import mta.jad.codenames.ui.api.dto.execution.chat.ChatMessage;
import mta.jad.codenames.ui.api.dto.global.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Builder
public class ChatActionsMock extends AbstractActiveGameMock {

    private int totalMessagesToProduce;
    private int messagesIntervalSeconds;
    private AtomicInteger totalMessagesProduced;
    private double sendMessageSuccessChance;
    private PlayerType[] playerTypes;

    @Override
    public void registerChatUpdates(Consumer<List<ChatMessage>> onChatMessagesUpdates) {
        Timer timer = new Timer();
        totalMessagesProduced = new AtomicInteger(0);
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (totalMessagesProduced.get() < totalMessagesToProduce) {
                    totalMessagesProduced.incrementAndGet();
                    List<ChatMessage> chatMessages = new ArrayList<>();
                    chatMessages.add(ChatMessage
                            .builder()
                            .message("message " + totalMessagesProduced.get())
                            .sender("sender " + totalMessagesToProduce)
                            .playerType(MockUtils.randomBoolean(0.5) ? playerTypes[0] : playerTypes[1])
                            .build());
                    onChatMessagesUpdates.accept(chatMessages);
                } else {
                    timer.cancel();
                }
            }
        }, messagesIntervalSeconds * 1000L, messagesIntervalSeconds * 1000L);
    }

    @Override
    public void sendMessage(String message, Runnable onSuccess, Consumer<String> onError) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessages.add(ChatMessage
                .builder()
                .message("message " + totalMessagesProduced.get())
                .sender("sender " + totalMessagesToProduce)
                .playerType(PlayerType.Guesser) // TODO: random it or think of configure it
                .build());

        if (MockUtils.randomBoolean(sendMessageSuccessChance)) {
            onSuccess.run();
        } else {
            onError.accept("Failed to send message");
        }
    }
}