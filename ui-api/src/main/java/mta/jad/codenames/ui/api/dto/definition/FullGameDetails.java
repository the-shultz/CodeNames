package mta.jad.codenames.ui.api.dto.definition;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
    A full representation of a game definition, containing all the details and aspects of it.
    This is used for displaying the full details of a game in the dashboard page once selected from the list of games.
 */
public class FullGameDetails {
    public String name;
    private GameStatus status;
    private int gameCardsCount;
    private int blackWordsCount;
    private List<TeamDetails> teams;

    public LightweightGameDetails toLightweightGameDetails() {
        return new LightweightGameDetails(name, teams.size(), status);
    }
}
