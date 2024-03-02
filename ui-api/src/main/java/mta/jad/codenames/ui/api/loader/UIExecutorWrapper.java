package mta.jad.codenames.ui.api.loader;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class UIExecutorWrapper {

    private final Executor executor;

    public UIExecutorWrapper(int threadsCount) {
        executor = Executors.newFixedThreadPool(threadsCount);
    }

    public void execute(Runnable command) {
        executor.execute(command);
    }
}
