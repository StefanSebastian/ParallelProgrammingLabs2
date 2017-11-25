package shop;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class UI {
    private Networking networking;
    private Scanner scanner;

    public UI(Networking networking) {
        this.networking = networking;
        scanner = new Scanner(System.in);
    }

    private void printMenu(){
        System.out.println("1.see products");
        System.out.println("2.send order");
    }

    public void runMenu(){
        while (true){
            printMenu();
            String opt = scanner.next();
            if (opt.equals("1")){
                seeProducts();
            } else if (opt.equals("2")){
                 sendOrder();
            } else {
                scanner.close();
                break;
            }
        }
    }

    private void seeProducts(){
        try {
            String prod = networking.getAllProducts();
            String[] products = prod.split(";");
            for (String p : products){
                System.out.println(p);
            }
        } catch (IOException e) {
            System.out.println("network error " + e.getMessage());
        }
    }

    private void sendOrder(){
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Product code: ");
        String code = scanner.next();
        System.out.println("Quantity: ");
        String quantity = scanner.next();

        try {
            String res = networking.postOrder(name, Integer.parseInt(code), Integer.parseInt(quantity));
            System.out.println(res);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Network error " + e.getMessage());
        }
    }
}
