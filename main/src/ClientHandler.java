import java.io.IOException;

public class ClientHandler implements Runnable {

    private Connection connection;

    public ClientHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msgFromClient = connection.receiveMessage();
                System.out.println("Client: " + msgFromClient);

                connection.sendMessage("Message received!");

                if (msgFromClient.equalsIgnoreCase("BYE")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close connection after handling communication
            connection.close();
        }
    }
}
