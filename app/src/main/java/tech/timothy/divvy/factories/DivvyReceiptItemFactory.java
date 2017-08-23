package tech.timothy.divvy.factories;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tech.timothy.divvy.divvyutil.DivvyRegex;
import tech.timothy.divvy.receipt.DebitedItem;
import tech.timothy.divvy.receipt.PointOfInterest;
import tech.timothy.divvy.receipt.ReceiptItem;
import tech.timothy.divvy.receipt.SubtotalItem;
import tech.timothy.divvy.receipt.TaxItem;
import tech.timothy.divvy.receipt.TotalItem;
import tech.timothy.divvy.receipt.UnknownItem;

/**
 * Created by timtan on 8/22/17.
 */

public class DivvyReceiptItemFactory {
    private static final String TAG = DivvyReceiptItemFactory.class.getSimpleName();

    public static ReceiptItem createDivvyReceiptItem(String line) {
        // create the regex for matching dollars
        Pattern dollarPattern = Pattern.compile(DivvyRegex.DOLLAR);

        // look for all matches
        ArrayList<Double> dollarValues = new ArrayList<>();
        Matcher dollarMatcher = dollarPattern.matcher(line);
        while (dollarMatcher.find()) {
            dollarValues.add(Double.parseDouble(dollarMatcher.group()));
        }

        // find all points of interest
        ArrayList<PointOfInterest> pointsOfInterest = new ArrayList<>();
        for (String word : line.split(DivvyRegex.WHITESPACE)) {
            for (PointOfInterest point : PointOfInterest.values()) {
                if (point.isMatch(word) && dollarValues.size() == 1) {
                    pointsOfInterest.add(point);
                }
            }
        }

        ReceiptItem receiptItem;
        if (pointsOfInterest.size() == 1 && dollarValues.size() == 1) {
            // In the ideal and sane case, we know what we have to instantiate
            switch (pointsOfInterest.get(0)) {
                case SUBTOTAL:
                    receiptItem = new SubtotalItem();
                    break;
                case TOTAL:
                    receiptItem = new TotalItem();
                    break;
                case TAX:
                    receiptItem = new TaxItem();
                    receiptItem.setDollarValue(dollarValues.get(0));
                    break;
                default:
                    receiptItem = new UnknownItem(pointsOfInterest, dollarValues, line);
                    break;
            }
        }
        else if (dollarValues.size() == 1 && pointsOfInterest.size() == 0) {
            // most likely a bilable item
            receiptItem = new DebitedItem();
        }
        else {
            // unknown item - logic to discard in receipt
            Log.i(TAG, "Creating unknown item - will most likely be discarded by receipt");
            receiptItem = new UnknownItem(pointsOfInterest, dollarValues, line);
        }

        return receiptItem;
    }
}
