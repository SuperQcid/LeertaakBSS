/**
 * Created by Thijs on 25/09/2015.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static DatabaseConnect database;
    /**
     * Make a new server socket and wait for clients to connect. Once a client connects with the server, a new instance of ServerHandler will be created.
     */
    public static void main(String args[]) {
        // Listen to server port (7789 is default port of Unwdmi generator)
        int portNumber = 7789;
        database = new DatabaseConnect();
        XMLParser xmlparser = new XMLParser();

        // Await connections of clients
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket socket;

            System.out.println("Server started on port " + portNumber);

            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket, xmlparser)).start();
            }

        // Catch potential exceptions
        } catch(IOException io){
            io.printStackTrace();
        }
    }

}