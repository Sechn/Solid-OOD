package edu.ticket.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Pattern - Single instance Logger with Observer pattern for multiple log destinations
 */
public class Logger {
    
    private static Logger instance;
    private List<LogObserver> observers;
    
    private Logger() {
        observers = new ArrayList<>();
    }
    
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    
    public void addObserver(LogObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(LogObserver observer) {
        observers.remove(observer);
    }
    
    public void log(String message) {
        for (LogObserver observer : observers) {
            observer.log(message);
        }
    }
}
