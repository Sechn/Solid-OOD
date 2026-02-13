package edu.ticket.strategy;

import edu.ticket.Ticket;

/**
 * Strategy Pattern - Interface for response strategies
 */
public interface ResponseStrategy {
    void sendResponse(Ticket ticket, String message);
}
