package mta.jad.codenames.api.impl.util;

import mta.jad.codenames.ui.api.dto.definition.FullGameDetails;
import mta.jad.codenames.ui.api.dto.definition.GameStatus;
import mta.jad.codenames.ui.api.dto.definition.TeamDetails;
import mta.jad.codenames.ui.api.dto.definition.TeamPlayers;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockUtils {

    private static final Random random = new Random();

    public static boolean randomBoolean(double chance) {
        return random.nextDouble() < chance;
    }

    public static FullGameDetails createRandomGameDetails(int gameNumber) {

        TeamDetails team1 = TeamDetails
                .builder()
                .name("Team 1")
                .cardsCount(random.nextInt(10))
                .definers(TeamPlayers.builder().totalRequired(1).totalRegistered(0).build())
                .guessers(TeamPlayers.builder().totalRequired(2).totalRegistered(0).build())
                .build();

        TeamDetails team2 = TeamDetails
                .builder()
                .name("Team 2")
                .cardsCount(random.nextInt(10))
                .definers(TeamPlayers.builder().totalRequired(2).totalRegistered(0).build())
                .guessers(TeamPlayers.builder().totalRequired(1).totalRegistered(0).build())
                .build();

        return
                FullGameDetails
                        .builder()
                        .name("Game " + gameNumber)
                        .blackWordsCount(random.nextInt(10))
                        .gameCardsCount(random.nextInt(30))
                        .status(GameStatus.PENDING)
                        .teams(Stream.of(team1, team2).collect(Collectors.toList()))
                        .build();
    }
}
