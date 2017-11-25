package shop.repository;
import shop.ShopException;
import shop.domain.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Repository implements IRepository{
    private Sold sold;
    private HashMap<Integer, Product> products;
    private HashMap<Integer, Sale> sales;
    private HashMap<Integer, Stock> stocks;
    private HashMap<Integer, Receipt> receipts;
    Integer saleCode;
    Integer receiptCode;

    private FileRepo fileRepo;

    public void setFileRepo(FileRepo fileRepo){
        this.fileRepo = fileRepo;
    }

    private void populateProducts(){
        Product p1 = new Product(1, "Masina", 10d, "a");
        products.put(1, p1);
        Product p2 = new Product(2, "Bicicleta", 14d, "a");
        products.put(2, p2);
        Product p3 = new Product(3, "Tren", 20d, "a");
        products.put(3, p3);

        stocks.put(p1.getProductCode(), new Stock(1, p1, 10));
        stocks.put(p2.getProductCode(), new Stock(2, p2, 4));
        stocks.put(p3.getProductCode(), new Stock(3, p3, 2));
    }

    public Repository() {
        saleCode = 0;
        receiptCode = 0;
        sold = new Sold(0d);
        products = new HashMap<>();
        sales = new HashMap<>();
        stocks = new HashMap<>();
        receipts = new HashMap<>();
        populateProducts();
    }

    public List<Product> getProducts() throws ShopException {
        return new ArrayList<>(products.values());
    }

    public Stock getStockForProduct(Integer productId)throws ShopException{
        return stocks.get(productId);
    }

    @Override
    public void setStockForProduct(Integer productId, Stock stock) throws ShopException {
        stocks.put(productId, stock);
    }

    @Override
    public synchronized Integer addSale(Integer productId, Integer quantity) throws ShopException {
        Product product = products.get(productId);
        saleCode++;
        Sale sale = new Sale(saleCode, product, quantity, new Date());
        sales.put(sale.getSaleCode(), sale);
        return sale.getSaleCode();
    }

    @Override
    public synchronized Receipt addReceipt(Integer saleId, String name) throws ShopException {
        Sale sale = sales.get(saleId);

        Double amount = sale.getQuantity() * sale.getProduct().getPriceUnit();

        receiptCode++;
        Receipt receipt = new Receipt(receiptCode, name, sale, amount);
        receipts.put(receiptCode, receipt);

        return receipt;
    }

    @Override
    public void saveState() throws ShopException {
        fileRepo.saveReceipts(new ArrayList<>(receipts.values()));
        fileRepo.saveSales(new ArrayList<>(sales.values()));
        fileRepo.saveStocks(new ArrayList<>(stocks.values()));
        fileRepo.saveSold(sold);

    }

    @Override
    public void incrementSold(Receipt receipt) throws ShopException {
        synchronized (sold) {
            sold.setAmount(sold.getAmount() + receipt.getTotalAmount());
        }
    }

    @Override
    public Double getSold() throws ShopException {
        return sold.getAmount();
    }

    @Override
    public List<Sale> getSalesAfter(Date date) throws ShopException {
        List<Sale> salesAfter = new ArrayList<>();
        for (Sale sale : sales.values()){
            if (sale.getDate().after(date)){
                salesAfter.add(sale);
            }
        }
        return salesAfter;
    }
}
