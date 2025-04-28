import java.io.*;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    // sends message to and from the sockets
    public void sendMessage(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    // Returns the message send that has been sent
    public String receiveMessage() throws IOException {
        return reader.readLine();
    }

    // Closes connection of the socket, reader and writer
    public void close() {
        try {
            if (socket != null) socket.close();
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}