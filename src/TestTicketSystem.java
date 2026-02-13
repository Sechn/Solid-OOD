import edu.ticket.Ticket;
import edu.ticket.TicketService;
import edu.ticket.factory.TicketFactory;
import edu.ticket.observer.Logger;
import edu.ticket.observer.ConsoleLogger;
import edu.ticket.strategy.TechnicalAssignmentStrategy;
import edu.ticket.strategy.EmailResponseStrategy;
import edu.ticket.state.CreatedState;

/**
 * Simple test cases to demonstrate the refactored system
 */
public class TestTicketSystem {

    public static void main(String[] args) {
        runAllTests();
    }

    public static void runAllTests() {
        System.out.println("  Running Ticketing System Tests  ");
       

        testFactoryPattern();
        testStatePattern();
        testAssignmentStrategy();
        testResponseStrategy();
        testSingletonLogger();
        testEndToEndWorkflow();

        System.out.println("All Tests Completed!");
       
    }

    private static void testFactoryPattern() {
        System.out.println(" Test 1: Factory Pattern");
        System.out.println("─────────────────────────────────────────────────────");

        Ticket ticket1 = TicketFactory.createBugTicket("WEB", "Test bug");
        Ticket ticket2 = TicketFactory.createEmailTicket("FEATURE", "Test feature");
        Ticket ticket3 = TicketFactory.createWebTicket("SUPPORT", "Test support");

        assert ticket1.getId() > 0 : "Ticket ID should be positive";
        assert ticket1.getChannel().equals("WEB") : "Channel should be WEB";
        assert ticket1.getType().equals("BUG") : "Type should be BUG";
        assert ticket2.getId() != ticket1.getId() : "IDs should be unique";

        System.out.println(" Factory Pattern Test Passed!");
        System.out.println("   - Created 3 different types of tickets");
        System.out.println("   - Auto-incremented IDs: " + ticket1.getId() + ", " 
                           + ticket2.getId() + ", " + ticket3.getId());
        System.out.println();
    }

    private static void testStatePattern() {
        System.out.println(" Test 2: State Pattern");
        System.out.println("─────────────────────────────────────────────────────");

        Ticket ticket = TicketFactory.createBugTicket("WEB", "State test");
        assert ticket.getStatus().equals("CREATED") : "Initial state should be CREATED";

        ticket.handleCurrentState();
        assert ticket.getStatus().equals("ASSIGNED") : "State should transition to ASSIGNED";

        ticket.handleCurrentState();
        assert ticket.getStatus().equals("IN_PROGRESS") : "State should transition to IN_PROGRESS";

        System.out.println(" State Pattern Test Passed!");
        System.out.println("   - State transitions work correctly");
        System.out.println("   - Final state: " + ticket.getStatus());
        System.out.println();
    }

    private static void testAssignmentStrategy() {
        System.out.println(" Test 3: Assignment Strategy Pattern");
        System.out.println("─────────────────────────────────────────────────────");

        Ticket bugTicket = TicketFactory.createBugTicket("WEB", "Test");
        TechnicalAssignmentStrategy strategy = new TechnicalAssignmentStrategy();
        strategy.assign(bugTicket);

        assert bugTicket.getAssignedTeam() != null : "Ticket should be assigned";
        assert bugTicket.getAssignedTeam().equals("ENGINEERING") 
               : "Bug should be assigned to ENGINEERING";

        System.out.println(" Assignment Strategy Test Passed!");
        System.out.println("   - Bug ticket assigned to: " + bugTicket.getAssignedTeam());
        System.out.println();
    }

    private static void testResponseStrategy() {
        System.out.println(" Test 4: Response Strategy Pattern");
        System.out.println("─────────────────────────────────────────────────────");

        Ticket ticket = TicketFactory.createEmailTicket("SUPPORT", "Test");
        EmailResponseStrategy strategy = new EmailResponseStrategy();
        strategy.sendResponse(ticket, "Test response message");

        assert ticket.getResponse() != null : "Response should be set";
        assert ticket.getResponse().equals("Test response message") 
               : "Response should match";

        System.out.println(" Response Strategy Test Passed!");
        System.out.println("   - Response sent via EMAIL strategy");
        System.out.println();
    }

    private static void testSingletonLogger() {
        System.out.println(" Test 5: Singleton Logger Pattern");
        System.out.println("─────────────────────────────────────────────────────");

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        assert logger1 == logger2 : "Logger should be singleton";
        
        logger1.addObserver(new ConsoleLogger());
        logger1.log("Test log message");

        System.out.println(" Singleton Logger Test Passed!");
        System.out.println("   - Same instance returned: " + (logger1 == logger2));
        System.out.println();
    }

    private static void testEndToEndWorkflow() {
        System.out.println(" Test 6: End-to-End Workflow");
        System.out.println("─────────────────────────────────────────────────────");

        // Setup
        Logger logger = Logger.getInstance();
        logger.addObserver(new ConsoleLogger());

        TicketService service = new TicketService();
        Ticket ticket = TicketFactory.createBugTicket("WEB", "Critical bug in production");

        String initialStatus = ticket.getStatus();
        service.handle(ticket);
        String finalStatus = ticket.getStatus();

        assert !initialStatus.equals(finalStatus) : "Status should change";
        assert ticket.getAssignedTeam() != null : "Ticket should be assigned";
        assert ticket.getResponse() != null : "Response should be sent";

        System.out.println(" End-to-End Workflow Test Passed!");
        System.out.println("   - Initial Status: " + initialStatus);
        System.out.println("   - Final Status: " + finalStatus);
        System.out.println("   - Assigned Team: " + ticket.getAssignedTeam());
        System.out.println("   - Response: " + ticket.getResponse());
        System.out.println();
    }
}
