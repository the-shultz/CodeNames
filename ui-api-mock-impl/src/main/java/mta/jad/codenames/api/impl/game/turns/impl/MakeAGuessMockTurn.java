package mta.jad.codenames.api.impl.game.turns.impl;

import mta.jad.codenames.ui.api.dto.execution.game.*;

public class MakeAGuessMockTurn extends AbstractMockedTurn {

    private final String word;

    public MakeAGuessMockTurn(int sleepTimeBefore, String word) {
        super(sleepTimeBefore);
        this.word = word;
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

        String currentTeamName = activeGameData.getCurrentTurn().getTeamName();

        // finds the team and updates its data, or else throws
        ActiveGameTeamDetails team = activeGameData.getTeams()
                .stream()
                .filter(t -> t.getName().equals(currentTeamName))
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
        if (team.getColor().name().equals(wordColor.name())) {
            team.setCurrentScore(team.getCurrentScore() + 1);
        } else {
            // its other's team word. find the team according to the color and increase its score
            ActiveGameTeamDetails otherTeam =
                    activeGameData
                            .getTeams()
                            .stream()
                            .filter(t -> t.getColor().name().equals(wordColor.name()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Team's word not found"));

            otherTeam.setCurrentScore(otherTeam.getCurrentScore() + 1);

            // if other team has guessed all its words, change his status to be a winner
            if (otherTeam.getCardsCount() == otherTeam.getCurrentScore()) {
                otherTeam.setStatus(ActiveGameTeamStatus.WINNER);
            }
        }

        // if the team has guessed all its remaining words, increase the number of turns
        if (activeGameData.getCurrentTurn().getNumberOfWordsRemainingToGuess() == 0) {
            team.setNumberOfTurns(team.getNumberOfTurns() + 1);
        }

        // if the team has guessed all its words, change his status to be a winner
        if (team.getCardsCount() == team.getCurrentScore()) {
            team.setStatus(ActiveGameTeamStatus.WINNER);
        }
    }
}
