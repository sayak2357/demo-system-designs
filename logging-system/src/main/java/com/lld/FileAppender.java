package com.lld;

// FileAppender (example – writes to file)
class FileAppender implements Appender {
    private final java.io.PrintWriter writer;

    public FileAppender(String fileName) throws java.io.IOException {
        this.writer = new java.io.PrintWriter(new java.io.FileWriter(fileName, true));
    }

    @Override
    public void append(String message) {
        synchronized (writer) {
            writer.println(message);
            writer.flush();
        }
    }

    @Override
    public void close() {
        synchronized (writer) {
            writer.close();
        }
    }
}
