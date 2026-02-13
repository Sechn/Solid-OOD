package edu.ticket.strategy;

import edu.ticket.Ticket;

public class SMSResponseStrategy implements ResponseStrategy {
    
    @Override
    public void sendResponse(Ticket ticket, String message) {
        System.out.println("Sending response via SMS to ticket #" + ticket.getId());
        System.out.println("SMS text: " + message);
        ticket.setResponse(message);
    }
}
