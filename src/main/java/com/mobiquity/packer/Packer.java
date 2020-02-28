package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.fileparser.FileParser;
import com.mobiquity.model.Item;

/**
 * Current class is used for find best solution how to get max count of {@link Item} with max weight.
 * Inside used knapsack algorithm {@link en.wikipedia.org/wiki/Knapsack_problem}
 *
 * @author igor.sila (isila)
 */
public class Packer {

    private Packer() {
    }

    /**
     * Current method is takes absolute path to file and returns back the index items taken into package
     *
     * @param filePath absolute path to file with test cases for item package weight and items
     * @return a result string with numbers of indexes of items which were taken into package
     * @throws {@link APIException} if file does not exist or contains invalid data
     */
    public static String pack(final String filePath) throws APIException {
        final var builder = new StringBuilder();
        final var parser = new FileParser();
        final var packageList = parser.parseFile(filePath);

        packageList.forEach(itemPackage -> builder.append("\n")
                                                  .append(KnapsackItemsFinder.findItems(itemPackage)));
        return builder.toString();
    }

}
