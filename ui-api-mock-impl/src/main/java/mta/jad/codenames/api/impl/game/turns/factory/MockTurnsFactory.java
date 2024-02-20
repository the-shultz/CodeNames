package mta.jad.codenames.api.impl.game.turns.factory;

import mta.jad.codenames.api.impl.game.turns.impl.MakeADefinitionMockTurn;
import mta.jad.codenames.api.impl.game.turns.impl.MakeAGuessMockTurn;
import mta.jad.codenames.api.impl.game.turns.impl.SwitchActiveTeamMockTurn;
import mta.jad.codenames.api.impl.game.turns.api.MockedTurn;

public class MockTurnsFactory {

    public static MockedTurn createGuessTurn(int sleepTimeBefore, String word, String teamName) {
        return new MakeAGuessMockTurn( sleepTimeBefore, word, teamName );
    }

    public static MockedTurn createDefinitionTurn(int sleepTimeBefore, String definition, int totalWords) {
        return new MakeADefinitionMockTurn( sleepTimeBefore, definition, totalWords );
    }

    public static MockedTurn createSwitchActiveTeamTurn(int sleepTimeBefore, String nextTeamName) {
        return new SwitchActiveTeamMockTurn( sleepTimeBefore, nextTeamName);
    }
}
