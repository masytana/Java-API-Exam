import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Starting server at http://127.0.0.1:8000");
            HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 8000),
                0
            );

            TicketController ticketController = new TicketController();

            addRoute(
                server,
                "/",
                Map.of("GET", () -> {
                    try {
                        String html = Files.readString(Paths.get("index.html"));
                        return new HttpResponseHandler(200, html.length(), html);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return new HttpResponseHandler(500, 0, "Erreur lors de la lecture du fichier HTML.");
                    }
                })
            );

            addRoute(
                server,
                "/tickets",
                Map.of(
                    "POST", () -> ticketController.enqueue(0),
                    "GET", () -> ticketController.dequeue()
                )
            );

            addRoute(
                server,
                "/tickets/peek",
                Map.of("GET", () -> ticketController.peek())
            );

            addRoute(
                server,
                "/tickets/empty",
                Map.of("GET", () -> ticketController.isEmpty())
            );

            addRoute(
                server,
                "/tickets/size",
                Map.of("GET", () -> ticketController.size())
            );

            server.start();
            System.out.println("Server started successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static <T> void addRoute(
        HttpServer server,
        String path,
        Map<String, Callable<HttpResponseHandler>> methodHandling
    ) throws Exception {
        server.createContext(path, exchange -> {
            String requestMethod = exchange.getRequestMethod();

            if (methodHandling.containsKey(requestMethod)) {
                try {
                    HttpResponseHandler resp = methodHandling.get(requestMethod).call();
                    String response = resp.getContent();
                    byte[] responseBytes = response.getBytes();

                    exchange.sendResponseHeaders(resp.getStatusCode(), responseBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(responseBytes);
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    String error = "Internal Server Error";
                    byte[] errorBytes = error.getBytes();
                    exchange.sendResponseHeaders(500, errorBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(errorBytes);
                    os.close();
                }
            } else {
                String notAllowed = "Method Not Allowed";
                byte[] notAllowedBytes = notAllowed.getBytes();
                exchange.sendResponseHeaders(405, notAllowedBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(notAllowedBytes);
                os.close();
            }
        });
    }
}

