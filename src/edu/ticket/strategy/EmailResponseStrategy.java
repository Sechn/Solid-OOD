package edu.ticket.strategy;

import edu.ticket.Ticket;

public class EmailResponseStrategy implements ResponseStrategy {
    
    @Override
    public void sendResponse(Ticket ticket, String message) {
        System.out.println("Sending response via EMAIL to ticket #" + ticket.getId());
        System.out.println("Email content: " + message);
        ticket.setResponse(message);
    }
}
