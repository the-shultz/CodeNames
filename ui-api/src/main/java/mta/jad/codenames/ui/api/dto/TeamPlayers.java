package mta.jad.codenames.ui.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
    Represents the details of a players in a team's group
    Contains the number of players required, the number of players registered, and the list of players names.
 */
public class TeamPlayers {
    private int totalRequired;
    private int totalRegistered;
    private List<String> players;
}
