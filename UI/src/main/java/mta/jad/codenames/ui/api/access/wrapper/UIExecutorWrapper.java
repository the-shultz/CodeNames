package mta.jad.codenames.ui.api.access.wrapper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class UIExecutorWrapper {

    private final Executor executor;
    private final String name;
    private int requestId;

    public UIExecutorWrapper(String name, int threadsCount ) {
        executor = Executors.newFixedThreadPool(threadsCount);
        this.name = name;
        requestId = 0;
    }

    public void execute(Runnable command) {
        final int currentRequestId = ++requestId;
        System.out.println("Submitting " + name + " command # " + currentRequestId);
        executor.execute(() -> {
            System.out.println("Executing " + name + " command # " + currentRequestId + " [" + Thread.currentThread().getName() + "]");
            command.run();
        });
    }
}
