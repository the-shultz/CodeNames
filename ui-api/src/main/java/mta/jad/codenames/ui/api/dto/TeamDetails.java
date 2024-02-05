package mta.jad.codenames.ui.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDetails {
    private String name;
    private int cardsCount;
    private TeamPlayers definers;
    private TeamPlayers guessers;
}
