package mta.jad.codenames.ui.api.dto.execution.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mta.jad.codenames.ui.api.dto.global.PlayerType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private long timestamp;
    private PlayerType playerType;
    private String sender;
    private String message;
}
