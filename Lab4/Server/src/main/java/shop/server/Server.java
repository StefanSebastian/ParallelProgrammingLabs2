package shop.server;

import org.springframework.beans.factory.annotation.Autowired;
import shop.ShopException;
import shop.controller.Controller;
import shop.controller.IController;
import shop.controller.OrderResult;
import shop.repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Server extends AbstractServer{
    @Autowired
    private IController controller;

    private ExecutorService executorService = Executors.newFixedThreadPool(8);

    void setController(IController controller){
        this.controller = controller;
    }

    public Server(Integer port) {
        super(port);
    }

    @Override
    protected void processRequest(Socket socketClient) {
        try {
            System.out.println("Started processing");
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(socketClient.getInputStream()));

            PrintWriter out =
                    new PrintWriter(socketClient.getOutputStream(), true);

            System.out.println("Reading data");
            String req = in.readLine();
            String[] arr = req.split("#");
            String code = arr[0];
            String quantity = arr[1];
            String name = arr[2];

            System.out.println("Order: " + code + " " + quantity + " " + name);

            Future<OrderResult> orderResult = controller.buyProduct(Integer.parseInt(code), Integer.parseInt(quantity), name);

            executorService.submit(() -> {
                try {
                    OrderResult or = orderResult.get();
                    if (or.getReceipt() != null){
                        out.write(or.getReceipt().getName() + "," + or.getReceipt().getTotalAmount() + "\n");
                    } else if (or.getMessage() != null){
                        out.write(or.getMessage() + "\n");
                    }
                    out.flush();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                        out.close();
                       // socketClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException | ShopException e) {
            e.printStackTrace();
        }
    }
}
