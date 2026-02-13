package edu.ticket.strategy;

import edu.ticket.Ticket;

public class TechnicalAssignmentStrategy implements AssignmentStrategy {
    
    @Override
    public void assign(Ticket ticket) {
        System.out.println("Assigned to Technical/Engineering Team");
        ticket.setAssignedTeam("ENGINEERING");
    }
}
