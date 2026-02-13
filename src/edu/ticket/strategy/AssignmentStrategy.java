package edu.ticket.strategy;

import edu.ticket.Ticket;

/**
 * Strategy Pattern - Interface for assignment strategies
 */
public interface AssignmentStrategy {
    void assign(Ticket ticket);
}
