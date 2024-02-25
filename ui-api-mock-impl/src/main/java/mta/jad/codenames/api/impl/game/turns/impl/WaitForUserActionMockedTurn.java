package mta.jad.codenames.api.impl.game.turns.impl;

import mta.jad.codenames.api.impl.game.impl.LatchWrapper;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

public class WaitForUserActionMockedTurn extends AbstractMockedTurn {

    private LatchWrapper latch;

    @Override
    public void setLatch(LatchWrapper latch) {
        this.latch = latch;
    }

    @Override
    protected void internalPerform(ActiveGameData activeGameData) {
        latch.createLatch();
    }
}
