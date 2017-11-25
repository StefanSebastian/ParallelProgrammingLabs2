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
import java.net.Socket;
import java.util.concurrent.Future;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Server extends AbstractServer{
    @Autowired
    private IController controller;

    void setController(IController controller){
        this.controller = controller;
    }

    public Server(Integer port) {
        super(port);
    }

    @Override
    protected void processRequest(Socket socketClient) {
        try {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(socketClient.getInputStream()));

            String req = in.readLine();
            String[] arr = req.split("#");
            String code = arr[0];
            String quantity = arr[1];
            String name = arr[2];

            System.out.println("Order: " + code + " " + quantity + " " + name);

            Future<OrderResult> orderResult = controller.buyProduct(Integer.parseInt(code), Integer.parseInt(quantity), name);

        } catch (IOException | ShopException e) {
            e.printStackTrace();
        }
    }
}
