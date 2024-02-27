package mta.jad.codenames.ui.api.dto.execution.game;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveGameData {

    private String name;
    private CurrentTurnDetails currentTurn;

    @Singular
    private List<ActiveGameTeamDetails> teams;

    @Singular
    private List<WordData> words;

    private int rows;
    private int columns;
}
