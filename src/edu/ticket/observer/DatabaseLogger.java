package edu.ticket.observer;

public class DatabaseLogger implements LogObserver {
    
    @Override
    public void log(String message) {
        // Simulating database logging
        System.out.println("[DATABASE LOG] Saving to database: " + message);
        // In real implementation: insert into database
    }
}
