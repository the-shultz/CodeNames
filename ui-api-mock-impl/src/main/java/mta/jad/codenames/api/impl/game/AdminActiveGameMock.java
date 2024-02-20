package mta.jad.codenames.api.impl.game;

import mta.jad.codenames.api.impl.game.turns.api.MockedTurn;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameTeamDetails;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameTeamStatus;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AdminActiveGameMock extends AbstractActiveGameMock {

    private final ActiveGameData baseGameDetails;
    private final List<MockedTurn> turns;
    private Consumer<ActiveGameData> onActiveGameUpdates;
    private WinLooseStatus onWinnerLooserUpdates;
    private final Map<String, ActiveGameTeamStatus> teamStatus;

    public AdminActiveGameMock(ActiveGameData baseGameDetails, List<MockedTurn> turns) {
        this.baseGameDetails = baseGameDetails;
        this.turns = turns;

        //traverse all teams of the game and init the teamStatus map with their initial status ActiveGameTeamStatus.IN_GAME
        teamStatus =
                baseGameDetails
                        .getTeams()
                        .stream()
                        .collect(Collectors.toMap(ActiveGameTeamDetails::getName, team -> ActiveGameTeamStatus.IN_GAME));
    }

    @Override
    public void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates) {
        this.onActiveGameUpdates = onActiveGameUpdates;
        startSequence();
    }

    @Override
    public void registerWinnerLooserUpdates(WinLooseStatus onWinnerLooserUpdates) {
        this.onWinnerLooserUpdates = onWinnerLooserUpdates;
        startSequence();
    }

    private void startSequence() {
        // invoke the sequence only if both onActiveGameUpdates and onWinnerLooserUpdates were given and are not null
        if (onActiveGameUpdates == null || onWinnerLooserUpdates == null) {
            return;
        }

        new Thread(() ->
                turns
                    .forEach(turn -> {
                        turn.perform(baseGameDetails);

                        // update the teamStatus map with the new status of the teams.
                        // if it was found that a team's status is changed, call the onWinnerLooserUpdates with the current status
                        baseGameDetails
                                .getTeams()
                                .forEach(team -> {
                                    ActiveGameTeamStatus currentStatus = teamStatus.get(team.getName());
                                    ActiveGameTeamStatus newStatus = team.getStatus();
                                    if (currentStatus != newStatus) {
                                        teamStatus.put(team.getName(), newStatus);
                                        onWinnerLooserUpdates.update(team.getName(), newStatus,
                                                newStatus == ActiveGameTeamStatus.WINNER ?
                                                        "The team " + team.getName() + " won the game!" :
                                                        "The team " + team.getName() + " lost the game!");
                                    }
                                });

                        onActiveGameUpdates.accept(baseGameDetails);
                    }))
                .start();
    }
}