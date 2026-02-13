package edu.ticket.observer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements LogObserver {
    
    private String filename;
    
    public FileLogger(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.println("[" + timestamp + "] " + message);
            System.out.println("[FILE LOG] Written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
