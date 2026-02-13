package edu.ticket.factory;

import edu.ticket.Ticket;
import edu.ticket.state.CreatedState;

/**
 * Factory Pattern - Factory class for creating Ticket objects
 */
public class TicketFactory {
    
    private static int ticketCounter = 1;
    
    public static Ticket createTicket(String channel, String type) {
        Ticket ticket = new Ticket(ticketCounter++, channel, type);
        ticket.setState(new CreatedState());
        return ticket;
    }
    
    public static Ticket createWebTicket(String type, String request) {
        Ticket ticket = createTicket("WEB", type);
        ticket.setRequest(request);
        return ticket;
    }
    
    public static Ticket createEmailTicket(String type, String request) {
        Ticket ticket = createTicket("EMAIL", type);
        ticket.setRequest(request);
        return ticket;
    }
    
    public static Ticket createBugTicket(String channel, String bugDescription) {
        Ticket ticket = createTicket(channel, "BUG");
        ticket.setRequest(bugDescription);
        return ticket;
    }
    
    public static Ticket createFeatureRequest(String channel, String featureDescription) {
        Ticket ticket = createTicket(channel, "FEATURE");
        ticket.setRequest(featureDescription);
        return ticket;
    }
}
