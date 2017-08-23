package tech.timothy.divvy.receipt;

/**
 * Created by timtan on 8/21/17.
 */

public class TaxItem extends ReceiptItem implements SummableItem {
    private Double percent = 0.0;

    public TaxItem(Double dollarValue) {
        initialize();
        this.setDollarValue(dollarValue);
    }

    public TaxItem() {
        initialize();
    }

    private void initialize() {
        this.setName(PointOfInterest.TAX.getName());
        this.setUntaxable();
    }

    public Double getSum() {
        return 0.0;
    }
}
