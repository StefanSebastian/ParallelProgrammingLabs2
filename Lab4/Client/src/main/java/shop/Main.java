package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        int port = 3001;

        Socket socket = new Socket(hostname, port);
        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
        out.write("1#2#gheorghe");

        out.close();
        socket.close();
    }
}
