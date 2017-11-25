package shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Networking networking = new Networking("localhost", 3001);
        UI ui = new UI(networking);
        ui.runMenu();
    }
}
