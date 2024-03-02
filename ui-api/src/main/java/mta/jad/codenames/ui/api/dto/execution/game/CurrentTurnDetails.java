package mta.jad.codenames.ui.api.dto.execution.game;

import lombok.*;
import mta.jad.codenames.ui.api.dto.global.TeamColor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentTurnDetails {
    private String teamName;
    private String definition;
    private TeamColor teamColor;
    private int numberOfWordsRemainingToGuess;
}
