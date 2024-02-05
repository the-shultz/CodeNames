package mta.jad.codenames.ui.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FullGameDetails {
    public String name;
    private GameStatus status;
    private int gameCardsCount;
    private int blackWordsCount;
    private List<TeamDetails> teams;
}
