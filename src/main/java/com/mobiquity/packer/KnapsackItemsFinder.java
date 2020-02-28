package com.mobiquity.packer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mobiquity.model.Item;
import com.mobiquity.model.ItemPackage;

/**
 * Current class takes {@link ItemPackage} and trying to build items with max weight
 *
 * @author igor.sila (isila)
 */
public class KnapsackItemsFinder {

    private static final String EMPTY_RESULT = "-";

    private KnapsackItemsFinder() {
    }

    /**
     * Current method trying to find best items with weight max closer to package weight
     *
     * @param itemPackage the {@link ItemPackage}
     * @return item indexes as String line for current package
     */
    public static String findItems(final ItemPackage itemPackage) {

        //if there is no items, return empty result
        if (Objects.isNull(itemPackage.getItems()) || itemPackage.getItems().isEmpty()) {
            return EMPTY_RESULT;
        }

        final var totalItemsWeight = itemPackage.getItems()
                                .stream()
                                .mapToDouble(Item::getWeight)
                                .sum();

        if(totalItemsWeight < itemPackage.getMaxWeight()) {
            return printItemIndexes(itemPackage.getItems());
        } else {
            return printItemIndexes(findBestItems(itemPackage));
        }
    }

    /**
     * Current method trying to find best items with weight max closer to package weight.
     * Tried knapsack algorithm to find best ratio.
     * {@link www.baeldung.com/java-knapsack} - example of knapsack solving
     *
     * @param itemPackage the {@link ItemPackage}
     * @return list of items
     */
    private static List<Item> findBestItems(final ItemPackage itemPackage) {
        final var itemsCount = itemPackage.getItems().size();
        final var possibleSolutions = new double[itemsCount + 1][itemPackage.getMaxWeight() + 1];

        for (int index = 1; index <= itemsCount; ++index) {

            final var item = itemPackage.getItems().get(index - 1);

            for (int weight = 1; weight <= itemPackage.getMaxWeight(); weight++) {
                if (item.getWeight() > weight) {
                    possibleSolutions[index][weight] = possibleSolutions[index - 1][weight];
                } else {

                    final double itemCost = item.getCost() + possibleSolutions[index - 1][weight - item.getWeight()];
                    //if new cost is better than previous, reset with better cost to make it with better cost
                    possibleSolutions[index][weight] = Math.max(itemCost, possibleSolutions[index - 1][weight]);
                }
            }
        }
        return findBestSolution(itemPackage, possibleSolutions);
    }

    private static List<Item> findBestSolution(final ItemPackage itemPackage, final double[][] possibleSolutions) {
        final var itemsCount = itemPackage.getItems().size();

        var packageMaxWeight = itemPackage.getMaxWeight();

        final List<Item> itemsToSelect = new ArrayList<>();

        //reversed iteration because in the last row and column there is max cost for current package
        for (int index = itemsCount; index > 0; index--) {

            //if the total cost is different in the last row and column and the previous row,
            // it means the part of solution, otherwise we need to skip item
            if (possibleSolutions[index][packageMaxWeight] != possibleSolutions[index - 1][packageMaxWeight]) {
                Item item = itemPackage.getItems().get(index - 1);
                itemsToSelect.add(item);

                // updating max weight
                packageMaxWeight -= item.getWeight();
            }
        }
        return itemsToSelect;
    }


    private static String printItemIndexes(final List<Item> items) {
        if(Objects.isNull(items) || items.isEmpty()) {
            return EMPTY_RESULT;
        }

        return items.stream()
                    .map(Item::getIndex)
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
    }
}
