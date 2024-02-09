package mta.jad.codenames.ui.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/*
    Represents the details of a single team in a game.
    Contains the name of the team, the number of cards assigned to the team, and the players in the team, of both groups: definers and guessers.
 */
public class TeamDetails {
    private String name;
    private int cardsCount;
    private TeamPlayers definers;
    private TeamPlayers guessers;
}
