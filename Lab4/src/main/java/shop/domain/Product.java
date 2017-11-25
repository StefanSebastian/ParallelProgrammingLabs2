package shop.domain;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Product {
    private Integer productCode;
    private String name;
    private Double priceUnit;
    private String measureUnit;

    public Product() {
    }

    public Product(Integer productCode, String name, Double priceUnit, String measureUnit) {
        this.productCode = productCode;
        this.name = name;
        this.priceUnit = priceUnit;
        this.measureUnit = measureUnit;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Double priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}
