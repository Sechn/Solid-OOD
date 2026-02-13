package edu.ticket.state;

import edu.ticket.Ticket;

public class AssignedState implements TicketState {
    
    @Override
    public void handle(Ticket ticket) {
        String type = ticket.getType();
        
        if (type.equals("BUG")) {
            System.out.println("Assigned to engineering");
        } else {
            System.out.println("Assigned to support");
        }
        
        // Transition to next state
        ticket.setState(new InProgressState());
    }
    
    @Override
    public String getStateName() {
        return "ASSIGNED";
    }
}
