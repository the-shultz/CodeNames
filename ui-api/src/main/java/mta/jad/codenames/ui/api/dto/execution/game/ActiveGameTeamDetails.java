package mta.jad.codenames.ui.api.dto.execution.game;

import lombok.*;
import mta.jad.codenames.ui.api.dto.global.TeamColor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveGameTeamDetails {
    private String name;
    private TeamColor color;
    private int cardsCount;
    private int currentScore;
    private int numberOfTurns;
}
