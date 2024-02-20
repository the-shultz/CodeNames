package mta.jad.codenames.api.impl.game;

import mta.jad.codenames.api.impl.game.turns.api.MockedTurn;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

import java.util.List;
import java.util.function.Consumer;

public class AdminActiveGameMock extends AbstractActiveGameMock {

    private final ActiveGameData baseGameDetails;
    private final List<MockedTurn> turns;

    public AdminActiveGameMock(ActiveGameData baseGameDetails, List<MockedTurn> turns) {
        this.baseGameDetails = baseGameDetails;
        this.turns = turns;
    }

    @Override
    public void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates) {

        new Thread(() ->
                turns
                    .forEach(turn -> {
                        turn.perform(baseGameDetails);
                        onActiveGameUpdates.accept(baseGameDetails);
                    }))
                .start();
    }

    @Override
    public void registerWinnerLooserUpdates(WinLooseStatus onWinnerLooserUpdates) {

    }
}
