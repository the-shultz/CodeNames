package mta.jad.codenames.api.impl.dashboard;

import lombok.Builder;
import mta.jad.codenames.api.impl.util.MockUtils;
import mta.jad.codenames.ui.api.dto.FullGameDetails;
import mta.jad.codenames.ui.api.dto.LightweightGameDetails;
import mta.jad.codenames.ui.api.dto.TeamDetails;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Builder
public class GamesDashboardUserMock extends AbstractGameDashboardMock {

    private final int totalGamesToProduce;
    private final int totalSecondsIntervalForGameProduction;

    @Override
    public void registerLightweightGameDetailsCallback(Consumer<List<LightweightGameDetails>> onGameDetailsChanged) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (games.size() < totalGamesToProduce) {
                    FullGameDetails fullGameDetails = MockUtils.createRandomGameDetails(games.size() + 1);
                    games.put(fullGameDetails.getName(), fullGameDetails);

                    List<LightweightGameDetails> lightWeightGames = games
                            .values()
                            .stream()
                            .map(FullGameDetails::toLightweightGameDetails)
                            .collect(Collectors.toList());

                    onGameDetailsChanged.accept(lightWeightGames);
                } else {
                    timer.cancel();
                }
            }
        }, totalSecondsIntervalForGameProduction * 1000L, totalSecondsIntervalForGameProduction * 1000L);
    }

    @Override
    public void registerDefiner(String gameName, String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure) {

        if (!games.containsKey(gameName)) {
            onRegistrationFailure.accept("Game not found");
            return;
        }

        FullGameDetails fullGameDetails = games.get(gameName);

        try {
            TeamDetails teamDetails =
                fullGameDetails
                    .getTeams()
                    .stream()
                    .filter(team -> team.getName().equals(teamName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Team not found"));

            teamDetails.getDefiners().setTotalRegistered(teamDetails.getDefiners().getTotalRegistered() + 1);
            teamDetails.getDefiners().getPlayers().add(playerName);
            onRegistrationSuccess.accept(fullGameDetails);

        } catch (RuntimeException e) {
            onRegistrationFailure.accept("Team not found");
        }
    }

    @Override
    public void registerGuesser(String gameName, String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure) {
        if (!games.containsKey(gameName)) {
            onRegistrationFailure.accept("Game not found");
            return;
        }

        FullGameDetails fullGameDetails = games.get(gameName);

        try {
            TeamDetails teamDetails =
                fullGameDetails
                    .getTeams()
                    .stream()
                    .filter(team -> team.getName().equals(teamName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Team not found"));

            teamDetails.getGuessers().setTotalRegistered(teamDetails.getGuessers().getTotalRegistered() + 1);
            teamDetails.getGuessers().getPlayers().add(playerName);
            onRegistrationSuccess.accept(fullGameDetails);

        } catch (RuntimeException e) {
            onRegistrationFailure.accept("Team not found");
        }
    }
}
