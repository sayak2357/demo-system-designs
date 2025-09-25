package com.lld;

public interface Appender {
    void append(String formattedMessage);
    // Default no-op; only needed for resources like FileAppender
    default void close() {}
}
