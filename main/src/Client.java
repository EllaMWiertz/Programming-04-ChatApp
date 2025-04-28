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

        // Allow user to choose a nickname
        System.out.println("Please enter a nickname: ");

        String nickname = scanner.nextLine();
        connection.sendMessage(nickname);
        System.out.println(nickname + " has entered the chat.");

        // Keep running until msgToSend equal 'bye'
        while (true) {

            String msgToSend = scanner.nextLine(); // reads input from the console
            connection.sendMessage(msgToSend);

            String reply = connection.receiveMessage();
            System.out.println("Server: " + reply);

            // Allows client to leave the server
            if (msgToSend.equalsIgnoreCase("QUIT")) {
                System.out.println(nickname + " has left the chat");
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


