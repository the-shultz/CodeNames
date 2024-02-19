package mta.jad.codenames.api.impl.game.turns;

public class MockTurnsFactory {

    public static MockedTurn createGuessTurn(int sleepTimeBefore, String word, String teamName) {
        return new MakeAGuessMockTurn( sleepTimeBefore, word, teamName );
    }
}
