package mta.jad.codenames.api.impl.dashboard;

import mta.jad.codenames.ui.api.dashboard.GamesDashboard;
import mta.jad.codenames.ui.api.dto.FullGameDetails;
import mta.jad.codenames.ui.api.dto.LightweightGameDetails;

import java.util.List;
import java.util.function.Consumer;

public class GamesDashboardMock implements GamesDashboard {


    @Override
    public void registerLightweightGameDetailsCallback(Consumer<List<LightweightGameDetails>> onGameDetailsChanged) {
        System.out.println("GamesDashboardMock.registerLightweightGameDetailsCallback");
    }

    @Override
    public void getGameDetails(String gameName, Consumer<FullGameDetails> gameDetails, Consumer<String> fullGameDetailsFailure) {
        System.out.println("GamesDashboardMock.getGameDetails");
    }

    @Override
    public void loadNewGame(String gameXmlPath, String dictionaryPath, Runnable onGameLoaded, Consumer<String> onGameLoadFailure) {
        System.out.println("GamesDashboardMock.loadNewGame");
    }

    @Override
    public void registerDefiner(String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure) {
        System.out.println("GamesDashboardMock.registerDefiner");
    }

    @Override
    public void registerGuesser(String teamName, String playerName, Consumer<FullGameDetails> onRegistrationSuccess, Consumer<String> onRegistrationFailure) {
        System.out.println("GamesDashboardMock.registerGuesser");
    }
}
