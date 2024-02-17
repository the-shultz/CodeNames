package mta.jad.codenames.ui.api.dto.definition;

import lombok.*;
import mta.jad.codenames.ui.api.dto.global.TeamColor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
    Represents the details of a single team in a game.
    Contains the name of the team, the number of cards assigned to the team, and the players in the team, of both groups: definers and guessers.
 */
public class TeamDetails {
    private String name;
    private int cardsCount;
    private TeamColor color;
    private TeamPlayers definers;
    private TeamPlayers guessers;
}
