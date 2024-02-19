package mta.jad.codenames.api.impl.game.turns;

import mta.jad.codenames.ui.api.dto.execution.game.*;

public class MakeAGuessMockTurn extends AbstractMockedTurn {

    private final String word;
    private final String teamName;

    // package friendly constructor specifically to allow access only to MockTurnsFactory
    MakeAGuessMockTurn(int sleepTimeBefore, String word, String teamName) {
        super(sleepTimeBefore);
        this.word = word;
        this.teamName = teamName;
    }

    @Override
    public void internalPerform(ActiveGameData activeGameData) {

        // find the word. throw if not found
        WordData wordData = activeGameData.getWords()
                .stream()
                .filter(word -> word.getWord().equals(this.word))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Word not found"));

        // set the word as revealed
        wordData.setRevealed(true);
        WordColor wordColor = wordData.getWordColor();

        // decrease the number of words remaining to guess
        activeGameData.getCurrentTurn().setNumberOfWordsRemainingToGuess(activeGameData.getCurrentTurn().getNumberOfWordsRemainingToGuess() - 1);

        // finds the team and updates its data, or else throws
        ActiveGameTeamDetails team = activeGameData.getTeams()
                .stream()
                .filter(t -> t.getName().equals(teamName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        // if its a neutral word, do nothing
        if (wordColor.equals(WordColor.NEUTRAL)) {
            return;
        }

        // if its a black word, the team loses
        if (wordColor.equals(WordColor.BLACK)) {
            team.setStatus(ActiveGameTeamStatus.LOSER);
        }

        // if its the team's color, increase the score
        if (team.getColor().equals(wordColor)) {
            team.setCurrentScore(team.getCurrentScore() + 1);
        }

        // if the team has guessed all its words, increase the number of turns
        if (activeGameData.getCurrentTurn().getNumberOfWordsRemainingToGuess() == 0) {
            team.setNumberOfTurns(team.getNumberOfTurns() + 1);
        }
    }
}
