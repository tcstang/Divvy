package tech.timothy.divvy.receipt;

/**
 * Created by timtan on 8/22/17.
 */

public class SubtotalItem extends ReceiptItem {
    public SubtotalItem(Double dollarValue) {
        initialize();

    }

    public SubtotalItem() {
        initialize();
    }

    private void initialize() {
        this.setName(PointOfInterest.SUBTOTAL.getName());
    }
}
