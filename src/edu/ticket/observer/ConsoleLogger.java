package edu.ticket.observer;

public class ConsoleLogger implements LogObserver {
    
    @Override
    public void log(String message) {
        System.out.println("[CONSOLE LOG] " + message);
    }
}
