package tech.timothy.divvy.receipt;

/**
 * Created by timtan on 8/21/17.
 */

public abstract class ReceiptItem {
    private String name;
    private Boolean isTaxed = false;
    private int quantity = 1;
    protected Double dollarValue;

    public void setDollarValue(Double dollarValue) {
        this.dollarValue = dollarValue;
    }

    public Double getDollarValue() {
        return this.dollarValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTaxable() {
        this.isTaxed = true;
    }

    public void setUntaxable() {
        this.isTaxed = false;
    }

    public void setQuanity(int quantity) {
        this.quantity = quantity;
    }
}
