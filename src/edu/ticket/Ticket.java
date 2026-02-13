package edu.ticket;

import edu.ticket.state.TicketState;

public class Ticket {
    private int id;
    private TicketState state;
    private String channel;
    private String type;
    private String request;
    private String response;
    private String assignedTeam;
    
    public Ticket(int id, String channel, String type) {
        this.id = id;
        this.channel = channel;
        this.type = type;
    }
    
    public void handleCurrentState() {
        if (state != null) {
            state.handle(this);
        }
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public TicketState getState() {
        return state;
    }
    
    public void setState(TicketState state) {
        this.state = state;
    }
    
    public String getStatus() {
        return state != null ? state.getStateName() : "UNKNOWN";
    }
    
    public String getChannel() {
        return channel;
    }
    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getRequest() {
        return request;
    }
    
    public void setRequest(String request) {
        this.request = request;
    }
    
    public String getResponse() {
        return response;
    }
    
    public void setResponse(String response) {
        this.response = response;
    }
    
    public String getAssignedTeam() {
        return assignedTeam;
    }
    
    public void setAssignedTeam(String assignedTeam) {
        this.assignedTeam = assignedTeam;
    }
}
