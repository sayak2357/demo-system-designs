package services;

import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * Simple async event bus: publish events and notify subscribers. Thread-safe.
 */
public class EventBus {
    private final ExecutorService executor;

    public EventBus(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public <T> void publish(final T event, final Consumer<T> handler) {
        executor.submit(() -> {
            try {
                handler.accept(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}