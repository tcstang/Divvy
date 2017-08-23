package tech.timothy.divvy.receipt;

import tech.timothy.divvy.divvyutil.DivvyUtils;

/**
 * Created by timtan on 8/23/17.
 */
public enum PointOfInterest {
    TOTAL("Total", "(total|balance)", 0.8),
    SUBTOTAL("Sub-total", "sub-?total", 0.8),
    TAX("Tax", "(tax|taxes)", 0.6);

    private final String name;
    private final String regex;
    private Double threshold;
    private int index = -1;

    PointOfInterest(String name, String regex, Double threshold) {
        this.name = name;
        this.regex = regex;
        this.threshold = threshold;
    }

    public String getRegex() {
                           return this.regex;
                                             }

    public String getName() {
                          return this.name;
                                           }

    public Boolean isMatch(String text) {
        return DivvyUtils.areStringsSimilar(text, this.getName(), this.threshold);
    }

    public int getIndex() {
                        return index;
                                     }

    public void setIndex(int index) {
        this.index = index;
    }
}
