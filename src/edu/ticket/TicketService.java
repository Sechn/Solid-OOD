package edu.ticket;

import edu.ticket.observer.Logger;
import edu.ticket.strategy.AssignmentStrategy;
import edu.ticket.strategy.ResponseStrategy;
import edu.ticket.strategy.GeneralAssignmentStrategy;
import edu.ticket.strategy.TechnicalAssignmentStrategy;
import edu.ticket.strategy.WebResponseStrategy;
import edu.ticket.strategy.EmailResponseStrategy;

public class TicketService {
    
    private AssignmentStrategy assignmentStrategy;
    private ResponseStrategy responseStrategy;
    private Logger logger;
    
    public TicketService() {
        logger = Logger.getInstance();
    }
    
    public void handle(Ticket ticket) {
        // Select appropriate assignment strategy based on ticket type
        selectAssignmentStrategy(ticket);
        
        // Select appropriate response strategy based on channel
        selectResponseStrategy(ticket);
        
        // Process ticket through all states
        while (!ticket.getStatus().equals("CLOSED")) {
            String previousState = ticket.getStatus();
            ticket.handleCurrentState();
            
            // If we're in ASSIGNED state, use assignment strategy
            if (ticket.getStatus().equals("ASSIGNED") && assignmentStrategy != null) {
                assignmentStrategy.assign(ticket);
            }
            
            // If we're in IN_PROGRESS state, send response
            if (ticket.getStatus().equals("IN_PROGRESS") && responseStrategy != null) {
                String responseMessage = generateResponse(ticket);
                responseStrategy.sendResponse(ticket, responseMessage);
            }
            
            // Prevent infinite loop if state doesn't change
            if (previousState.equals(ticket.getStatus())) {
                break;
            }
        }
        
        // Log the final state
        logger.log("Ticket handling completed: " + ticket.getId() + " -> " + ticket.getStatus());
    }
    
    private void selectAssignmentStrategy(Ticket ticket) {
        if (ticket.getType().equals("BUG") || ticket.getType().equals("TECHNICAL")) {
            assignmentStrategy = new TechnicalAssignmentStrategy();
        } else {
            assignmentStrategy = new GeneralAssignmentStrategy();
        }
    }
    
    private void selectResponseStrategy(Ticket ticket) {
        if (ticket.getChannel().equals("WEB")) {
            responseStrategy = new WebResponseStrategy();
        } else if (ticket.getChannel().equals("EMAIL")) {
            responseStrategy = new EmailResponseStrategy();
        } else {
            responseStrategy = new WebResponseStrategy(); // Default
        }
    }
    
    private String generateResponse(Ticket ticket) {
        if (ticket.getType().equals("BUG")) {
            return "Thank you for reporting this bug. Our engineering team has investigated and resolved the issue.";
        } else {
            return "Thank you for contacting us. Your request has been processed.";
        }
    }
    
    public void setAssignmentStrategy(AssignmentStrategy strategy) {
        this.assignmentStrategy = strategy;
    }
    
    public void setResponseStrategy(ResponseStrategy strategy) {
        this.responseStrategy = strategy;
    }
}
