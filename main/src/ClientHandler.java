import java.io.IOException;

public class ClientHandler implements Runnable {

    private Connection connection;

    public ClientHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {

            // Gets users nickname
            String clientNickname = connection.receiveMessage();

            while (true) {

                String msgFromClient = connection.receiveMessage();
                System.out.println(clientNickname + ": " + msgFromClient);

                connection.sendMessage("Message received!");

                if (msgFromClient.equalsIgnoreCase("QUIT")) {
                    System.out.println(clientNickname + " has left the server.");
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
