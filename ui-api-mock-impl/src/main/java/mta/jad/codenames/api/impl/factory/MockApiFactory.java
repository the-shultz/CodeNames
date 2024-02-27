package mta.jad.codenames.api.impl.factory;

import mta.jad.codenames.api.impl.dashboard.GamesDashboardAdminMock;
import mta.jad.codenames.api.impl.dashboard.GamesDashboardUserMock;
import mta.jad.codenames.api.impl.game.chat.ChatActionsMock;
import mta.jad.codenames.api.impl.game.impl.AdminActiveGameMock;
import mta.jad.codenames.api.impl.game.turns.api.MockedTurn;
import mta.jad.codenames.api.impl.game.turns.factory.MockTurnsFactory;
import mta.jad.codenames.api.impl.login.LoginMock;
import mta.jad.codenames.ui.api.dashboard.GamesDashboard;
import mta.jad.codenames.ui.api.dto.execution.game.*;
import mta.jad.codenames.ui.api.dto.global.PlayerType;
import mta.jad.codenames.ui.api.dto.global.TeamColor;
import mta.jad.codenames.ui.api.game.ActiveGame;
import mta.jad.codenames.ui.api.game.chat.ChatActions;
import mta.jad.codenames.ui.api.login.Login;

import java.util.ArrayList;
import java.util.List;

public class MockApiFactory {

    public static Login createSuccessfulLogin() {
        return
            LoginMock
                .builder()
                .adminLoginFailureChance(0.0)
                .userLoginFailureChance(0.0)
                .adminLoginResult(true)
                .build();

    }

    public static Login createFailedLogin() {
        return
            LoginMock
                .builder()
                .adminLoginFailureChance(1.0)
                .userLoginFailureChance(1.0)
                .adminLoginResult(false)
                .build();
    }

    public static GamesDashboard createAdminGamesDashboard() {
        return
            GamesDashboardAdminMock
                    .builder()
                    .addNewGameFailureChance(0.0)
                    .build();
    }

    public static GamesDashboard createUserGamesDashboard() {
        return
            GamesDashboardUserMock
                    .builder()
                    .totalGamesToProduce(2)
                    .totalSecondsIntervalForGameProduction(2)
                    .build();
    }

    public static ChatActions createDefaultChat() {
        return
            ChatActionsMock
                .builder()
                .messagesIntervalSeconds(3)
                .sendMessageSuccessChance(1)
                .totalMessagesToProduce(10)
                .playerTypes(new PlayerType[]{PlayerType.Definer, PlayerType.Guesser})
                .build();
    }

    public static ActiveGame createAdminActiveGame() {
        List<MockedTurn> turns = new ArrayList<>();
        turns.add(MockTurnsFactory.createSwitchActiveTeamTurn(1000, "T1"));
        turns.add(MockTurnsFactory.createDefinitionTurn(1000, "fruits", 2));
        turns.add(MockTurnsFactory.createGuessTurn(1000, "apple"));
        turns.add(MockTurnsFactory.createGuessTurn(1000, "paper"));
        turns.add(MockTurnsFactory.createSwitchActiveTeamTurn(1000, "T2"));
        turns.add(MockTurnsFactory.createDefinitionTurn(1000, "vegetables", 2));
        turns.add(MockTurnsFactory.createGuessTurn(1000, "snake"));

        ActiveGameData activeGameData = createActiveGameData();
        return new AdminActiveGameMock(createDefaultChat(), activeGameData, turns);
    }

    public static ActiveGame createPlayerActiveGame() {
        List<MockedTurn> turns = new ArrayList<>();
        turns.add(MockTurnsFactory.createSwitchActiveTeamTurn(1000, "T1"));
        turns.add(MockTurnsFactory.createWaitForUserAction()); // for definition matching one word
        turns.add(MockTurnsFactory.createWaitForUserAction()); // for guessing one word
        turns.add(MockTurnsFactory.createSwitchActiveTeamTurn(1000, "T2"));
        turns.add(MockTurnsFactory.createWaitForUserAction()); // for user end turn

        ActiveGameData activeGameData = createActiveGameData();
        return new AdminActiveGameMock(createDefaultChat(), activeGameData, turns);
    }

    private static ActiveGameData createActiveGameData() {
        return
                ActiveGameData
                        .builder()
                        .name("Game 1")
                        .rows(3)
                        .columns(3)

                        // words
                        .word(WordData.builder().word("apple").wordColor(WordColor.BLUE).build())
                        .word(WordData.builder().word("work").wordColor(WordColor.BLUE).build())
                        .word(WordData.builder().word("camera").wordColor(WordColor.BLUE).build())
                        .word(WordData.builder().word("glass").wordColor(WordColor.RED).build())
                        .word(WordData.builder().word("pen").wordColor(WordColor.RED).build())
                        .word(WordData.builder().word("wire").wordColor(WordColor.RED).build())
                        .word(WordData.builder().word("paper").wordColor(WordColor.NEUTRAL).build())
                        .word(WordData.builder().word("phone").wordColor(WordColor.NEUTRAL).build())
                        .word(WordData.builder().word("snake").wordColor(WordColor.BLACK).build())

                        // teams
                        .team(ActiveGameTeamDetails
                                .builder()
                                .name("T1")
                                .color(TeamColor.BLUE)
                                .cardsCount(3)
                                .currentScore(0)
                                .numberOfTurns(0)
                                .status(ActiveGameTeamStatus.IN_GAME)
                                .build())
                        .team(ActiveGameTeamDetails
                                .builder()
                                .name("T2")
                                .color(TeamColor.RED)
                                .cardsCount(3)
                                .currentScore(0)
                                .numberOfTurns(0)
                                .status(ActiveGameTeamStatus.IN_GAME)
                                .build())
                        .build();
    }
}