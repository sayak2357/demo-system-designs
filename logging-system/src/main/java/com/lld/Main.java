package com.lld;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        System.out.println("Hello from Logging System");
        Logger logger = Logger.getInstance();

        // Add ConsoleAppender
        logger.addAppender(new ConsoleAppender());

        // Add FileAppender
        logger.addAppender(new FileAppender("app.log"));

        logger.setLogLevel(LogLevel.DEBUG);

        logger.debug("Debugging...");
        logger.info("Application started");
        logger.warn("Low memory warning");
        logger.error("Unhandled exception occurred");
    }
}