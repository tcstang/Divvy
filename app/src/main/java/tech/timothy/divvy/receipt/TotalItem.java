package tech.timothy.divvy.receipt;

/**
 * Created by timtan on 8/22/17.
 */

public class TotalItem extends ReceiptItem {
    public TotalItem() {
        initialize();
    }

    public TotalItem(Double dollarValue) {
        initialize();
    }

    public void initialize() {
        this.setName(PointOfInterest.TOTAL.getName());
    }
}
