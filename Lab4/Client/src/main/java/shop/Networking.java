package shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Networking {
    private String hostname;
    private int port;

    public Networking(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getAllProducts() throws IOException{
        Socket socket = new Socket(hostname, port);

        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);

        out.write("getProducts\n");
        out.flush();

        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        String res = in.readLine();

        in.close();
        out.close();
        socket.close();

        return res;
    }

    public String postOrder(String name, Integer productCode, Integer quantity) throws IOException{
        Socket socket = new Socket(hostname, port);
        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);

        out.write("postOrder\n");
        out.flush();
        out.write(productCode + "#" + quantity + "#" + name +"\n");
        out.flush();

        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        String response = in.readLine();

        in.close();
        out.close();
        socket.close();

        return response;
    }
}
