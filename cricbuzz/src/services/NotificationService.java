package services;


import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/**
 * Publisher-subscriber for live updates. In production this would be backed by
 * Redis Pub/Sub, Kafka, or WebSocket connections. Here we keep it simple.
 */
public class NotificationService<T> {
    private final CopyOnWriteArraySet<Consumer<T>> subscribers = new CopyOnWriteArraySet<>();

    public void subscribe(Consumer<T> consumer) {
        subscribers.add(consumer);
    }

    public void unsubscribe(Consumer<T> consumer) {
        subscribers.remove(consumer);
    }

    public void publish(T message) {
        for (Consumer<T> s : subscribers) {
            try { s.accept(message); } catch (Exception ignored) {}
        }
    }
}
