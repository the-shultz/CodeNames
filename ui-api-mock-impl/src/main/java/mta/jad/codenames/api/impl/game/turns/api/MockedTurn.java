package mta.jad.codenames.api.impl.game.turns.api;

import mta.jad.codenames.api.impl.game.impl.LatchWrapper;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

public interface MockedTurn {
    void perform(ActiveGameData activeGameData);
    void setLatch(LatchWrapper latch);
}
