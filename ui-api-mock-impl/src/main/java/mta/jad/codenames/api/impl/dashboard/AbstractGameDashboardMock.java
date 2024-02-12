package mta.jad.codenames.api.impl.dashboard;

import mta.jad.codenames.ui.api.dashboard.GamesDashboard;
import mta.jad.codenames.ui.api.dto.FullGameDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractGameDashboardMock implements GamesDashboard {

    protected Map<String, FullGameDetails> games;

    public AbstractGameDashboardMock() {
        games = new HashMap<>();
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
    public void registerDefiner(String gameName, String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure) {
        System.out.println("GamesDashboardMock.registerDefiner | USER ONLY method");
    }

    @Override
    public void registerGuesser(String gameName, String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure) {
        System.out.println("GamesDashboardMock.registerDefiner | USER ONLY method");
    }

    @Override
    public void loadNewGame(String gameXmlPath, String dictionaryPath, Runnable onGameLoaded, Consumer<String> onGameLoadFailure) {
        System.out.println("GamesDashboardMock.loadNewGame | ADMIN ONLY method");
    }

}
