package edu.ticket.strategy;

import edu.ticket.Ticket;

public class WebResponseStrategy implements ResponseStrategy {
    
    @Override
    public void sendResponse(Ticket ticket, String message) {
        System.out.println("Sending response via WEB interface to ticket #" + ticket.getId());
        System.out.println("Web notification: " + message);
        ticket.setResponse(message);
    }
}
