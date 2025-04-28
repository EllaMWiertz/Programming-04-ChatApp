import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private Connection connection;

    public Server() throws IOException {

        // Initialise the server socket
        serverSocket = new ServerSocket(9999);
    }

    public void serverHandler() throws IOException {
        System.out.println("Server is running... Waiting on clients");

        // This loop ensures the server stays running
        while (true) {
            try {
                // Accept a client connection and gets the socket
                socket = serverSocket.accept();
                connection = new Connection(socket);

                ClientHandler clienthandler = new ClientHandler(connection);
                Thread thread = new Thread(clienthandler);
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.serverHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}