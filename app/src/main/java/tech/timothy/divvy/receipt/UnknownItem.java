package tech.timothy.divvy.receipt;

import java.util.ArrayList;

/**
 * Created by timtan on 8/21/17.
 */

public class UnknownItem extends ReceiptItem {
    private ArrayList<PointOfInterest> pointsOfInterest;
    private ArrayList<Double> dollarValues;
    private String rawData;

    public UnknownItem(ArrayList<PointOfInterest> pointsOfInterest, ArrayList<Double> dollarValues, String line) {
        this.pointsOfInterest = pointsOfInterest;
        this.dollarValues = dollarValues;
        this.rawData = line;
    }
}
