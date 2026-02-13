package edu.ticket.state;

import edu.ticket.Ticket;

public class InProgressState implements TicketState {
    
    @Override
    public void handle(Ticket ticket) {
        System.out.println("Working on ticket");
        
        String type = ticket.getType();
        if (type.equals("BUG")) {
            System.out.println("Sending bug response");
        } else {
            System.out.println("Sending generic response");
        }
        
        // Transition to next state
        ticket.setState(new ResolvedState());
    }
    
    @Override
    public String getStateName() {
        return "IN_PROGRESS";
    }
}
