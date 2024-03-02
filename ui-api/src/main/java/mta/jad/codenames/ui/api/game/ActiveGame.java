package mta.jad.codenames.ui.api.game;

import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameTeamStatus;
import mta.jad.codenames.ui.api.game.admin.AdminActiveGame;
import mta.jad.codenames.ui.api.game.chat.ChatActions;
import mta.jad.codenames.ui.api.game.player.PlayerActiveGame;

public interface ActiveGame extends AdminActiveGame, PlayerActiveGame, ChatActions {

    /**
     * REACTIVE METHOD
     * Register for updates on the game status for either winning\loosing teams.
     * these updates will be delivered for any team that has changed its status for either be a winner (finished all its cards) or a looser (picked the black card).
     * this method should be called once per each team's update.
     * each update will contain the team name, the new status and the reason for the change.
     * A proper dialog will appear with the aforementioned information.
     * this method MUST return immediately, and the callback should be called on a different thread.
     * @param onWinnerLooserUpdates the callback to be called when one of the teams status is updated.
     */
    void registerWinnerLooserUpdates(WinLooseStatus onWinnerLooserUpdates);

    interface WinLooseStatus {

        void update(String teamName, ActiveGameTeamStatus activeGameTeamStatus, String reason);
    }
}
