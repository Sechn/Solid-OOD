import edu.ticket.Ticket;
import edu.ticket.TicketService;
import edu.ticket.factory.TicketFactory;
import edu.ticket.observer.Logger;
import edu.ticket.observer.ConsoleLogger;
import edu.ticket.observer.FileLogger;
import edu.ticket.observer.DatabaseLogger;

public class Main {

    public static void main(String[] args) {
        
        // Initialize Logger with observers (Singleton + Observer Pattern)
        Logger logger = Logger.getInstance();
        logger.addObserver(new ConsoleLogger());
        logger.addObserver(new FileLogger("tickets.log"));
        logger.addObserver(new DatabaseLogger());
        
        // Create TicketService
        TicketService ticketService = new TicketService();
        
        System.out.println("=== Example 1: Bug reported from Web ===");
        // Using Factory Pattern to create tickets
        Ticket ticket1 = TicketFactory.createBugTicket("WEB", "I see a very very BAD BUG!");
        ticketService.handle(ticket1);
        
        System.out.println("\n=== Example 2: Feature request from Email ===");
        Ticket ticket2 = TicketFactory.createEmailTicket("FEATURE", "Please add dark mode!");
        ticketService.handle(ticket2);
        
        System.out.println("\n=== Example 3: General support from Web ===");
        Ticket ticket3 = TicketFactory.createWebTicket("SUPPORT", "How do I reset my password?");
        ticketService.handle(ticket3);
        
        System.out.println("\n=== All tickets processed successfully! ===");
    }
}
