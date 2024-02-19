package mta.jad.codenames.ui.api.dto.execution.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class WordData {
    private String word;
    private WordColor wordColor;
    @Setter private boolean isRevealed;
}
