package shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        out.write("1#2#gheorghe\n");
        out.flush();

        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        String response = in.readLine();
        System.out.println("Response : " + response);

        in.close();
        out.close();
        socket.close();
    }
}
