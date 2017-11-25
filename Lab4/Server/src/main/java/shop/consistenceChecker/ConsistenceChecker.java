package shop.consistenceChecker;

import shop.ShopException;
import shop.controller.Controller;
import shop.controller.IController;
import shop.domain.Sale;
import shop.domain.Stock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class ConsistenceChecker implements Runnable {
    private Controller controller;

    // old values
    private Double oldSold;
    private Date oldTimestamp;
    private Map<Integer, Integer> oldStock;

    private final String loggerPath;

    private void cloneStocks(){
        try {
            Map<Integer, Stock> stockMap = controller.getStocks();

            oldStock = new HashMap<>();
            for (Integer prodKey : stockMap.keySet()){
                oldStock.put(prodKey, stockMap.get(prodKey).getQuantity());
            }

        } catch (ShopException e) {
            e.printStackTrace();
        }
    }

    public ConsistenceChecker(Controller controller){
        this.controller = controller;
        oldSold = 0d;
        oldTimestamp = new Date();
        cloneStocks();
        loggerPath = "E:\\Info\\anu3\\progr paralela\\labs2\\ParallelProgrammingLabs2\\Lab4\\Server\\src\\main\\resources\\shop\\checker.txt";
    }

    private void saveReport(String outcome, List<Sale> sales){

        StringBuilder text = new StringBuilder(outcome + "\n");
        text.append("The following sales were added\n");
        for (Sale sale : sales){
            text.append(sale.getProduct().getName())
                    .append("x").append(sale.getQuantity())
                    .append(" at ").append(sale.getDate())
                    .append("\n");
        }
        try {
            Files.write(Paths.get(loggerPath), text.toString().getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){

            controller.getReadWriteLock().writeLock().lock();

            try {
                // save state
                controller.saveState();

                // check consistence

                // check amount
                boolean error;

                Double current = controller.getSold();
                //System.out.println("Consistence Checker : Current sold " + current);
                Double diff = current - oldSold;
                //System.out.println("Consistence Checker : Difference " + diff);

                List<Sale> sales = controller.getSalesAfter(oldTimestamp);
                //System.out.println(sales.size() + " sales were made");

                Double amount = 0d;
                for (Sale sale : sales){
                    amount = amount + sale.getQuantity() * sale.getProduct().getPriceUnit();
                }

                //System.out.println("Amount transactioned : " + amount);

                error = !diff.equals(amount);

                // check stocks
                Map<Integer, Stock> stockMap = controller.getStocks();
                for (Sale sale : sales){
                    Integer currentStock = oldStock.get(sale.getProduct().getProductCode());
                    currentStock -= sale.getQuantity();
                    oldStock.put(sale.getProduct().getProductCode(), currentStock);
                }
                for (Integer prodCode : oldStock.keySet()){
                    if (!oldStock.get(prodCode).equals(stockMap.get(prodCode).getQuantity())){
                        error = true;
                        break;
                    }
                }

                if (error) {
                    saveReport("Invalid state", sales);
                } else {
                    saveReport("Correct values", sales);
                }


                // save old values
                oldSold = controller.getSold();
                oldTimestamp = new Date();
                cloneStocks();

            } catch (ShopException e){
                e.printStackTrace();
            } finally {
                controller.getReadWriteLock().writeLock().unlock();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
