package mta.jad.codenames.api.impl.game.turns.impl;

import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

public class MakeADefinitionMockTurn extends AbstractMockedTurn {

    private String definition;
    private int wordsToGuess;

    public MakeADefinitionMockTurn(int sleepTimeBefore, String definition, int wordsToGuess) {
        super(sleepTimeBefore);
        this.definition = definition;
        this.wordsToGuess = wordsToGuess;
    }

    @Override
    protected void internalPerform(ActiveGameData activeGameData) {
        activeGameData.getCurrentTurn().setDefinition(definition);
        activeGameData.getCurrentTurn().setNumberOfWordsRemainingToGuess(wordsToGuess);
    }
}
