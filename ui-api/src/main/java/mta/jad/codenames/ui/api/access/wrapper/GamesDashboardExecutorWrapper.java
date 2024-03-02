package mta.jad.codenames.ui.api.access.wrapper;

import mta.jad.codenames.ui.api.dashboard.GamesDashboard;
import mta.jad.codenames.ui.api.dto.definition.FullGameDetails;
import mta.jad.codenames.ui.api.dto.definition.LightweightGameDetails;

import java.util.List;
import java.util.function.Consumer;

public class GamesDashboardExecutorWrapper extends UIExecutorWrapper implements GamesDashboard {

    private final GamesDashboard delegate;

    public GamesDashboardExecutorWrapper(GamesDashboard delegate) {
        super(3);
        this.delegate = delegate;
    }

    @Override
    public void registerLightweightGameDetailsCallback(Consumer<List<LightweightGameDetails>> onGameDetailsChanged) {
        delegate.registerLightweightGameDetailsCallback(onGameDetailsChanged);
    }

    @Override
    public void getGameDetails(String gameName, Consumer<FullGameDetails> gameDetails, Consumer<String> fullGameDetailsFailure) {
        execute(() -> delegate.getGameDetails(gameName, gameDetails, fullGameDetailsFailure));
    }

    @Override
    public void loadNewGame(String gameXmlPath, String dictionaryPath, Runnable onGameLoaded, Consumer<String> onGameLoadFailure) {
        execute(() -> delegate.loadNewGame(gameXmlPath, dictionaryPath, onGameLoaded, onGameLoadFailure));
    }

    @Override
    public void registerDefiner(String gameName, String teamName, String playerName, Runnable onRegistrationSuccess, Consumer<String> onRegistrationFailure) {
        execute(() -> delegate.registerDefiner(gameName, teamName, playerName, onRegistrationSuccess, onRegistrationFailure));
    }

    @Override
    public void registerGuesser(String gameName, String teamName, String playerName, Runnable onRegistrationSuccess, Consumer<String> onRegistrationFailure) {
        execute(() -> delegate.registerGuesser(gameName, teamName, playerName, onRegistrationSuccess, onRegistrationFailure));
    }
}
