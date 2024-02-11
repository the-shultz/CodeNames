package mta.jad.codenames.api.impl.dashboard;

import mta.jad.codenames.api.impl.util.MockUtils;
import mta.jad.codenames.ui.api.dashboard.GamesDashboard;
import mta.jad.codenames.ui.api.dto.FullGameDetails;
import mta.jad.codenames.ui.api.dto.LightweightGameDetails;
import mta.jad.codenames.ui.api.dto.TeamDetails;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class GamesDashboardMock implements GamesDashboard {

    private int totalGamesToProduce = 0;
    private int totalSecondsIntervalForGameProduction = 2;
    private double addNewGameFailureChance = 0;

    private Consumer<List<LightweightGameDetails>> onGameDetailsChanged;
    private Map<String, FullGameDetails> games = new HashMap<>();

    @Override
    public void registerLightweightGameDetailsCallback(Consumer<List<LightweightGameDetails>> onGameDetailsChanged) {
        System.out.println("GamesDashboardMock.registerLightweightGameDetailsCallback");

        this.onGameDetailsChanged = onGameDetailsChanged;
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
        }, totalSecondsIntervalForGameProduction * 1000, totalSecondsIntervalForGameProduction * 1000);
    }

    @Override
    public void getGameDetails(String gameName, Consumer<FullGameDetails> gameDetails, Consumer<String> fullGameDetailsFailure) {
        if (games.containsKey(gameName)) {
            gameDetails.accept(games.get(gameName));
        } else {
            fullGameDetailsFailure.accept("Game not found");
        }
    }

    @Override
    public void loadNewGame(String gameXmlPath, String dictionaryPath, Runnable onGameLoaded, Consumer<String> onGameLoadFailure) {
        boolean failure = MockUtils.randomBoolean(addNewGameFailureChance);
        if (failure) {
            onGameLoadFailure.accept("Failed to load game");
        } else {
            FullGameDetails fullGameDetails = MockUtils.createRandomGameDetails(games.size() + 1);
            games.put(fullGameDetails.getName(), fullGameDetails);
            onGameLoaded.run();
        }
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
