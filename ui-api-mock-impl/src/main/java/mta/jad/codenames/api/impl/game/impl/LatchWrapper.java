package mta.jad.codenames.api.impl.game.impl;

import java.util.concurrent.CountDownLatch;

public class LatchWrapper {

    private CountDownLatch countDownLatch;

    public LatchWrapper() {
        countDownLatch = new CountDownLatch(0);
    }

    public void createLatch() {
        countDownLatch = new CountDownLatch(1);
    }

    public void countDown() {
        countDownLatch.countDown();
    }

    public void await() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
