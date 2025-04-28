import java.io.*;
import java.net.Socket;
import java.util.Scanner;



public class Client {
    private Socket socket;
    private Connection connection;
    private Scanner scanner;

    public Client() throws IOException {


        socket = new Socket("localhost", 9999); // Initialise the client socket
        connection = new Connection(socket);
        scanner = new Scanner(System.in);
    }

    public void msgHandler() throws IOException {
        // Keep running until msgToSend equal 'bye'
        while (true) {
            String msgToSend = scanner.nextLine(); // reads input from the console
            connection.sendMessage(msgToSend);

            String reply = connection.receiveMessage();
            System.out.println("Server: " + reply);

            if (msgToSend.equalsIgnoreCase("BYE")) {
                break;
            }
        }
        connection.close();
    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.msgHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


