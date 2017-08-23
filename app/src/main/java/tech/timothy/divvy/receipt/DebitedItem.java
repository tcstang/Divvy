package tech.timothy.divvy.receipt;

/**
 * Created by timtan on 8/21/17.
 */

public class DebitedItem extends ReceiptItem implements SummableItem {

    public Double getSum() {
        return this.dollarValue;
    }
}
