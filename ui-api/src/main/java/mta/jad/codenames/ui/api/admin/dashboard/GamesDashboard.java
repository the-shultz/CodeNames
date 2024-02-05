package mta.jad.codenames.ui.api.admin.dashboard;

import mta.jad.codenames.ui.api.dto.FullGameDetails;
import mta.jad.codenames.ui.api.dto.LightweightGameDetails;

import java.util.List;
import java.util.function.Consumer;

public interface GamesDashboard {

    void loadNewGame(String gameXmlPath, String dictionaryPath, Runnable onGameLoaded, Consumer<String> onGameLoadFailure);
    void getGameDetails(String gameName, Consumer<FullGameDetails> gameDetails, Consumer<String> fullGameDetailsFailure);

    void registerLightweightGameDetailsCallback(Consumer<List<LightweightGameDetails>> onGameDetailsChanged);
}
