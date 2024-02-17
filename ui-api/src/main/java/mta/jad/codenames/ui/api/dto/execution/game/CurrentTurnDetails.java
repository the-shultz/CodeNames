package mta.jad.codenames.ui.api.dto.execution.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mta.jad.codenames.ui.api.dto.global.TeamColor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentTurnDetails {
    private String teamName;
    private String definition;
    private TeamColor teamColor;
    private int numberOfWordsRemainingToGuess;
}
