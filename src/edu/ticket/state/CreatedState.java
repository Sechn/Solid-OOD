package edu.ticket.state;

import edu.ticket.Ticket;

public class CreatedState implements TicketState {
    
    @Override
    public void handle(Ticket ticket) {
        System.out.println("Ticket created");
        
        String channel = ticket.getChannel();
        if (channel.equals("WEB")) {
            System.out.println("Received from web");
        } else if (channel.equals("EMAIL")) {
            System.out.println("Received from email");
        }
        
        // Transition to next state
        ticket.setState(new AssignedState());
    }
    
    @Override
    public String getStateName() {
        return "CREATED";
    }
}
