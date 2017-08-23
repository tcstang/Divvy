package tech.timothy.divvy.divvyutil;

import java.util.HashSet;

/**
 * Created by timtan on 8/21/17.
 */

public class DivvyUtils {
    public static final String whitespaceRegex = "\\s+";

    public enum Regex {
        DOLLAR("[0-9]*\\.[0-9]{2}"),
        WHITESPACE("\\s+");

        private String value;

        private Regex(String regex) {
            this.value = regex;
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     let firstSet = _getSetOfPairs(self)
     let secondSet = _getSetOfPairs(str)

     let matchedSet = firstSet.intersection(secondSet)
     let result = Double(2 * matchedSet.count) / Double(firstSet.count + secondSet.count)

     return (result >= threshold)
     */
    public static Boolean areStringsSimilar(String str1, String str2, Double threshold) {
        HashSet<String> set1 = getSetsOfPairs(str1);
        HashSet<String> set2 = getSetsOfPairs(str2);

        // create union of set 1 and set 2
        HashSet<String> intersectedSet = new HashSet<>();
        intersectedSet.addAll(set1);
        intersectedSet.retainAll(set2);

        Double result = new Double(2 * intersectedSet.size()) / new Double(set1.size() + set2.size());

        return result >= threshold;
    }

    public static Boolean areStringsSimilar(String str1, String str2) {
        Double defaultThreshold = 0.8;
        return areStringsSimilar(str1, str2, 0.8);
    }

    /**
     * Recursive function that returns a set of strings of length 2
     * with the last string of length 1 if the word is odd.
     *
     * @param word the word
     */
    private static HashSet<String> getSetsOfPairs(String word) {
        HashSet<String> set = new HashSet<>();

        // base case a
        if (word.length() == 0) {
            // no-op
        }
        // base case b
        else if (word.length() == 1) {
            set.add(word);
            return set;
        }
        // recursive case
        else {
            set.add(word.substring(0, 2));
            set.addAll(getSetsOfPairs(word.substring(1)));
        }

        return set;
    }
}
