import java.io.IOException;

public class TicketController {

    private TicketService ticketService;

    public TicketController() {
        this.ticketService = new TicketService();
    }

    public HttpResponseHandler enqueue(int element) {
        Ticket ticket = ticketService.enqueue(element);
        String response =
            "{\n\t\"id\":" +
            ticket.getId() +
            ", \n\t\"agencyName\": " +
            ticket.getAgencyName() +
            ", \n\t\"boothNumber\": " +
            ticket.getBoothNumber() +
            "\n}";
        return new HttpResponseHandler(201, response.length(), response);
    }

    public HttpResponseHandler dequeue() {
        Ticket ticket = ticketService.dequeue();

        String response =
            "{\n\t\"id\":" +
            ticket.getId() +
            ", \n\t\"agencyName\": " +
            ticket.getAgencyName() +
            ", \n\t\"boothNumber\": " +
            ticket.getBoothNumber() +
            "\n}";

        return new HttpResponseHandler(200, response.length(), response);
    }

    public HttpResponseHandler peek() throws Exception, IOException {
        Ticket ticket = ticketService.peek();

        String response =
            "{\n\t\"id\":" +
            ticket.getId() +
            ", \n\t\"agencyName\": " +
            ticket.getAgencyName() +
            ", \n\t\"boothNumber\": " +
            ticket.getBoothNumber() +
            "\n}";

        return new HttpResponseHandler(200, response.length(), response);
    }

    public HttpResponseHandler isEmpty() {
        String response = "{\n\t\"isEmpty\":" + ticketService.isEmpty() + "\n}";
        return new HttpResponseHandler(200, response.length(), response);
    }

    public HttpResponseHandler size() {
        String response = "{\n\t\"size\": " + ticketService.size() + "\n}";
        return new HttpResponseHandler(200, response.length(), response);
    }
}
