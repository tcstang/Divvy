package tech.timothy.divvy.managers;

import java.util.ArrayList;
import java.util.regex.Pattern;

import tech.timothy.divvy.factories.DivvyReceiptItemFactory;
import tech.timothy.divvy.receipt.Receipt;

/**
 * Created by timtan on 8/14/17.
 */

public class ReceiptDataManager {
    private ReceiptDataManager() {}

    private static ReceiptDataManager instance;
    private String rawData;
    private ArrayList<String> textData = new ArrayList<>();
    private Receipt receipt;

    public static ReceiptDataManager getInstance() {
        if (instance == null) {
            instance = new ReceiptDataManager();
        }

        return instance;
    }

    public void setRawData(String data) {
        this.rawData = data;
    }

    public Receipt processReceiptData() throws NumberFormatException {
        if (rawData == null) {
            throw new NullPointerException("Please set receipt's raw data before processing");
        }

        // split data by new lines
        String[] receiptData = rawData.split("\n");

        // create the regex for matching dollars
        String dollarRegex = "[0-9]*\\.[0-9]{2}";
        Pattern dollarPattern = Pattern.compile(dollarRegex);

        for (String line : receiptData) {
            getReceipt().addItem(
                    DivvyReceiptItemFactory.createDivvyReceiptItem(line)
            );
        }

        return null;
    }

    private Boolean lineContainsDollarValue(String line) {
        return false;
    }

    private Boolean lineContainsPointOfInterest(String line) {
        return false;
    }

    public Receipt getReceipt() {
        if (receipt == null) {
            receipt = new Receipt();
        }

        return this.receipt;
    }

}
