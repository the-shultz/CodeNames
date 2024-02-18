package mta.jad.codenames.api.impl.game;

import mta.jad.codenames.ui.api.dto.execution.game.ActiveGameData;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class AdminActiveGameMock extends AbstractActiveGameMock {

    private ActiveGameData baseGameDetails;

    private List<MockSingleTurn> turns;
    private int delayBetweenTurnsInSeconds;

    @Override
    public void registerActiveGameUpdates(Consumer<ActiveGameData> onActiveGameUpdates) {

        new Thread(() -> {

            try {

                // delay before starting the turns sequence
                Thread.sleep(3000);




            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }
}
