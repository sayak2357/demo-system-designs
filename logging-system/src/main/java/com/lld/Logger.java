package com.lld;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger{
    private static volatile Logger instance;
    private LogLevel currentLevel;
    private List<Appender> appenders;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd--MM-yyyy HH:mm:ss");

    private Logger() {

        this.currentLevel = LogLevel.DEBUG; // default level
        this.appenders = new ArrayList<>();
    }
    public static Logger getInstance(){
        if(instance==null){
            synchronized (Logger.class){
                if(instance==null){
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public synchronized void setLogLevel(LogLevel level){
        this.currentLevel = level;
    }

    public synchronized void addAppender(Appender appender) {
        appenders.add(appender);
    }
    /*
    * Log messages now includes: timestamp+level+thread name
    * */
    private void log(LogLevel level,String message){
        if(level.getLevel()>= currentLevel.getLevel()){
            String timestamp = LocalDateTime.now().format(FORMATTER);
            String threadName = Thread.currentThread().getName();
            String formattedMessage =
                    String.format("[%s] [%s] [%s]: %s", timestamp, level, threadName, message);

            for (Appender appender : appenders) {
                appender.append(formattedMessage);
            }
        }
    }

    public void debug(String message) {log(LogLevel.DEBUG,message);}
    public void info(String message) {log(LogLevel.INFO,message);}
    public void warn(String message) {log(LogLevel.WARN,message);}
    public void error(String message) {log(LogLevel.ERROR,message);}
}
