package mta.jad.codenames.ui.api.dto.execution.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentTurnDetails {
    private String teamName;
    private String definition;
    private int numberOfWords;
}
