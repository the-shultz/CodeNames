package mta.jad.codenames.api.impl.game.turns.impl;

import lombok.Getter;
import mta.jad.codenames.api.impl.game.turns.api.MockedTurn;
import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

@Getter
public abstract class AbstractMockedTurn implements MockedTurn {

    private final int sleepTimeBefore;

    public AbstractMockedTurn() {
        this(0);
    }

    public AbstractMockedTurn(int sleepTimeBefore) {
        this.sleepTimeBefore = sleepTimeBefore;
    }

    @Override
    public void perform(ActiveGameData activeGameData) {
        try {
            Thread.sleep(sleepTimeBefore);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        internalPerform(activeGameData);
    }
    protected abstract void internalPerform(ActiveGameData activeGameData);
}
