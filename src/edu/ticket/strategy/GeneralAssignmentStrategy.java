package edu.ticket.strategy;

import edu.ticket.Ticket;

public class GeneralAssignmentStrategy implements AssignmentStrategy {
    
    @Override
    public void assign(Ticket ticket) {
        System.out.println("Assigned to General Support Team");
        ticket.setAssignedTeam("SUPPORT");
    }
}
