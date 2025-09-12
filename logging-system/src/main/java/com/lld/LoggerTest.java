package com.lld;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.setLogLevel(LogLevel.WARN);
        logger.error("error occurred");
        logger.info("info test");
        logger.setLogLevel(LogLevel.DEBUG);
        logger.info("info again");
    }
}
