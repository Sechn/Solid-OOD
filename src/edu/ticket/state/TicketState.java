package edu.ticket.state;

import edu.ticket.Ticket;

/**
 * State Pattern - Interface for different ticket states
 */
public interface TicketState {
    void handle(Ticket ticket);
    String getStateName();
}
