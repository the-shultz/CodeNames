package mta.jad.codenames.ui.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
  A lightweight representation of a game, containing only the name, the number of teams and the game status.
  This is used for displaying the list of games in the dashboard page.
 */
public class LightweightGameDetails {
    public String name;
    private int teamsCount;
    private GameStatus status;
}
