package mta.jad.codenames.api.impl.game;

import mta.jad.codenames.api.impl.game.turns.MockedTurn;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

import java.util.List;
import java.util.function.Consumer;

public class AdminActiveGameMock extends AbstractActiveGameMock {

    private ActiveGameData baseGameDetails;
    private List<MockedTurn> turns;

    @Override
    public void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates) {

    }
}
