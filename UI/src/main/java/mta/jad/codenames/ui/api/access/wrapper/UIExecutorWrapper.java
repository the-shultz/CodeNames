package mta.jad.codenames.ui.api.access.wrapper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class UIExecutorWrapper {

    private final static String THREAD_NAME_FORMAT="T #%s | [%s] pool";
    private final Executor executor;
    private final String name;
    private int requestId;
    private int threadNumber = 0;

    public UIExecutorWrapper(String name, int threadsCount ) {
        executor = Executors.newFixedThreadPool(threadsCount, r -> {
            threadNumber++;
            return new Thread(r, String.format(THREAD_NAME_FORMAT, threadNumber, name));
        });
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
