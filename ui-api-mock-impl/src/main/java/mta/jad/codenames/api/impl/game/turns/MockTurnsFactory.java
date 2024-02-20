package mta.jad.codenames.api.impl.game.turns;

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
