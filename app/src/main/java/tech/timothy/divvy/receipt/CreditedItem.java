package tech.timothy.divvy.receipt;

/**
 * Created by timtan on 8/23/17.
 */

public class CreditedItem extends ReceiptItem implements SummableItem {
    public Double getSum() {
        return this.getDollarValue() * -1;
    }
}
