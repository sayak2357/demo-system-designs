package com.lld;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static volatile Logger instance;
    private LogLevel currentLevel;

    private Logger() {
        this.currentLevel = LogLevel.DEBUG; // default level
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

    private void log(LogLevel level,String message){
        if(level.getLevel()>= currentLevel.getLevel()){
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String formattedMessage = String.format("[%s] [%s]: %s", timestamp, level, message);
            // ✅ Only synchronize the actual output step to avoid contention
            synchronized (System.out) {
                System.out.println(formattedMessage);
            }
        }
    }

    public void debug(String message) {log(LogLevel.DEBUG,message);}
    public void info(String message) {log(LogLevel.INFO,message);}
    public void warn(String message) {log(LogLevel.WARN,message);}
    public void error(String message) {log(LogLevel.ERROR,message);}
}
