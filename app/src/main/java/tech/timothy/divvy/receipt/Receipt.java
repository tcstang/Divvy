package tech.timothy.divvy.receipt;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by timtan on 8/21/17.
 */

public class Receipt {
    String TAG = Receipt.class.getSimpleName();

    /* the arraylist of all items found on the receipt except the total */
    private ArrayList<ReceiptItem> receiptItems = new ArrayList<>();

    /* subtotal */
    private Double subtotal;

    /** the total cost of all items on receipt */
    private Double total;

    public Receipt() {

    }

    public void addItem(ReceiptItem item) {
        receiptItems.add(item);
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void printItems() {
        for (ReceiptItem item : receiptItems) {
            Log.i(TAG, item.getName());
        }
    }
}
