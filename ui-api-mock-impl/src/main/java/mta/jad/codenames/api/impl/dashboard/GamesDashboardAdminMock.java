package mta.jad.codenames.api.impl.dashboard;

import lombok.Builder;
import mta.jad.codenames.api.impl.util.MockUtils;
import mta.jad.codenames.ui.api.dto.definition.FullGameDetails;
import mta.jad.codenames.ui.api.dto.definition.LightweightGameDetails;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Builder
public class GamesDashboardAdminMock extends AbstractGameDashboardMock {

    private final double addNewGameFailureChance;
    private final AtomicInteger totalGamesReported = new AtomicInteger(0);

    // every two seconds will check if there are new games to report
    @Override
    public void registerLightweightGameDetailsCallback(Consumer<List<LightweightGameDetails>> onGameDetailsChanged) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                    if (totalGamesReported.get() != games.size()) {
                        List<LightweightGameDetails> lightWeightGames = games
                                .values()
                                .stream()
                                .map(FullGameDetails::toLightweightGameDetails)
                                .collect(Collectors.toList());
                        totalGamesReported.set(games.size());
                        onGameDetailsChanged.accept(lightWeightGames);
                    }
            }
        }, 2000L, 2000L);
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
}
