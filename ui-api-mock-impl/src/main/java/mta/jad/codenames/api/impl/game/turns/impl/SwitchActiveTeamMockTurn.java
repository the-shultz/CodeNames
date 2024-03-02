package mta.jad.codenames.api.impl.game.turns.impl;

import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameTeamDetails;

public class SwitchActiveTeamMockTurn extends AbstractMockedTurn {

    private final String nextTeamTurn;

    public SwitchActiveTeamMockTurn(int sleepTimeBefore, String nextTeamTurn) {
        super(sleepTimeBefore);
        this.nextTeamTurn = nextTeamTurn;
    }

    @Override
    protected void internalPerform(ActiveGameData activeGameData) {
        ActiveGameTeamDetails nextTeam =
                activeGameData
                        .getTeams()
                        .stream()
                        .filter(team -> team.getName().equals(nextTeamTurn))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        activeGameData.getCurrentTurn().setTeamColor(nextTeam.getColor());
        activeGameData.getCurrentTurn().setTeamName(nextTeam.getName());
        activeGameData.getCurrentTurn().setDefinition("");
        activeGameData.getCurrentTurn().setNumberOfWordsRemainingToGuess(0);
    }
}
