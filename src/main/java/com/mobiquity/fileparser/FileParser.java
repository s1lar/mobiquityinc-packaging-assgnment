package com.mobiquity.fileparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemPackage;

/**
 * Current class is parsing input file from file path and transform it to {@link ItemPackage} with list of {@link Item}
 * During processing can throw {@link APIException} is input data is invalid for classes.
 *
 * @author igor.sila (isila)
 */
public class FileParser {

    /**
     * Regular expression to represent single input valid line
     * Valid line example: 75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16)
     */
    private static final String inputLineRegex =
            "^\\d+\\s*?:\\s*\\(\\s*\\d+\\s*,\\s*\\d*\\.?\\d+\\s*,\\s*€\\d*\\.?\\d+\\s*\\).*$";

    private static final String currencyRegex = "\\p{Sc}";

    /**
     * Regular expression to represent cleared line without spaces and additional
     * signs like euro, etc. for single {@link Item}
     * Valid cleared item example : (1,85.31,29)
     */
    private static final Pattern itemRegex = Pattern.compile("\\((\\d+),(\\d+\\.?\\d*?),(\\d+)\\)");

    private APIException exception;

    public List<ItemPackage> parseFile(final String filePath) throws APIException {
        final List<ItemPackage> packages = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(line -> {
                try {
                    packages.add(parseLine(line));
                } catch (APIException e) {
                    this.exception = e;
                }
            });
        } catch (IOException e) {
            throw new APIException("An exception occurred during parsing file", e);
        }
        if (Objects.nonNull(this.exception)) {
            throw new APIException("An exception occurred during parsing file", exception);
        }
        return Collections.unmodifiableList(packages);
    }

    private ItemPackage parseLine(final String line) throws APIException {
        if (!isLineValid(line)) {
            throw new APIException("An Exception occurred while parsing line");
        }

        //clear line from currency symbol
        final String[] inputParams = line.replaceAll(currencyRegex, "")
                                         .split(":");

        final int packageWeight = Integer.parseInt(inputParams[0].trim());

        final List<Item> items = new ArrayList<>();
        final Matcher itemMatcher = itemRegex.matcher(inputParams[1]);

        while (itemMatcher.find()) {
            final int index = Integer.parseInt(itemMatcher.group(1));
            final double weight = Double.parseDouble(itemMatcher.group(2));
            final double cost = Double.parseDouble(itemMatcher.group(3));

            //check if we need to add item with bigger weight than package weight
            if (packageWeight >= weight) {
                items.add(new Item(index, weight, cost));
            }
        }
        return new ItemPackage(packageWeight, items);
    }

    private boolean isLineValid(final String line) {
        return line.matches(inputLineRegex);
    }
}
