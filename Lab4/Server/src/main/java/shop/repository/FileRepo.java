package shop.repository;

import org.springframework.stereotype.Component;
import shop.domain.Receipt;
import shop.domain.Sale;
import shop.domain.Sold;
import shop.domain.Stock;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class FileRepo {
    private String stocksPath;
    private String receiptsPath;
    private String salesPath;
    private String soldPath;

    private void initialize(){
        String root = "E:\\Info\\anu3\\progr paralela\\labs2\\ParallelProgrammingLabs2\\Lab4\\Server\\src\\main\\resources\\shop\\";
        stocksPath = root + "stocks.txt";
        receiptsPath = root + "receipts.txt";
        salesPath = root + "sales.txt";
        soldPath = root + "sold.txt";
    }

    public FileRepo() {
        initialize();
    }

    public FileRepo(String stocksPath, String receiptsPath, String salesPath) {
        this.stocksPath = stocksPath;
        this.receiptsPath = receiptsPath;
        this.salesPath = salesPath;
    }

    public void saveStocks(List<Stock> stockList){
        try(PrintWriter printWriter = new PrintWriter(stocksPath, "UTF-8")){
            for (Stock stock : stockList){
                printWriter.write("product code:" + stock.getProduct().getProductCode() + ", Quantity: " + stock.getQuantity());
                printWriter.println();
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void saveReceipts(List<Receipt> receipts){
        try(PrintWriter printWriter = new PrintWriter(receiptsPath, "UTF-8")){
            for (Receipt receipt : receipts){
                printWriter.write("Name: " + receipt.getName() + ", Total value: "
                        + receipt.getTotalAmount() + " , Sale id" + receipt.getSale().getSaleCode());
                printWriter.println();
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void saveSales(List<Sale> sales){
        try(PrintWriter printWriter = new PrintWriter(salesPath, "UTF-8")){
            for (Sale sale : sales){
                printWriter.write("Code " + sale.getSaleCode() + ", Product code: "
                        + sale.getProduct().getProductCode() + ", Quantity" + sale.getQuantity() + ", Timestamp" + sale.getDate());
                printWriter.println();
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void saveSold(Sold sold){
        try(PrintWriter printWriter = new PrintWriter(soldPath, "UTF-8")){
            printWriter.write(sold.getAmount().toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
