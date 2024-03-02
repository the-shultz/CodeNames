package mta.jad.codenames.ui.api.dto.execution.chat;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameChat {

    @Singular
    private List<ChatMessage> deltaChatMessages;
}
