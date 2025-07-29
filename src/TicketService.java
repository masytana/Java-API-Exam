import java.util.ArrayList;
import java.util.List;

public class TicketService {

    public static List<Ticket> tickets = new ArrayList<>();
    public static int ticketNum = 0;

    public TicketService() {}

    public Ticket enqueue(int element) {
        Ticket ticket = new Ticket();
        ticket.setId(++ticketNum);
        ticket.setAgencyName("Agence Yas Ankorondrano");
        ticket.setBoothNumber(5);

        tickets.add(ticket);
        return ticket;
    }

    public Ticket dequeue() {
        return tickets.remove(0);
    }

    public Ticket peek() throws Exception {
        if (tickets.size() == 0) {
            throw new Exception("The ticket list is empty");
        }
        return tickets.get(0);
    }

    public boolean isEmpty() {
        return tickets.isEmpty();
    }

    public int size() {
        return tickets.size();
    }
}
