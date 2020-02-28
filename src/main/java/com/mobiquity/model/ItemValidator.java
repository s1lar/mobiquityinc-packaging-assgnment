package com.mobiquity.model;

import com.mobiquity.exception.APIException;
import org.apache.commons.lang3.Range;

/**
 * Representing validation class to {@link Item} and {@link ItemPackage}
 *
 * @author igor.sila (isila)
 */
public class ItemValidator {

    public static final int POSITIVE_INDEX = 0;
    public static final int MAX_PACKAGE_WEIGHT = 100;
    public static final int MAX_ITEMS_IN_PACKAGE = 15;
    public static final double MIN_ITEM_WEIGHT = 0;
    public static final double MAX_ITEM_WEIGHT = 100;
    public static final double MIN_ITEM_COST = 0;
    public static final double MAX_ITEM_COST = 100;

    public static void validateItem(final int index, final double itemWeight, final double itemCost)
            throws APIException {
        checkItemIndex(index);
        checkItemWeight(itemWeight);
        checkItemCost(itemCost);
    }

    public static void validateItemPackage(final int itemsCount, final int packageWeight) throws APIException {
        checkItemPackageCount(itemsCount);
        checkItemPackageWeight(packageWeight);
    }

    private static void checkItemIndex(final int index) throws APIException {
        if (index > POSITIVE_INDEX) {
            return;
        }
        throw new APIException("Item index could not be negative");
    }

    private static void checkItemWeight(final double itemWeight) throws APIException {
        final Range<Double> itemWeightRange = Range.between(MIN_ITEM_WEIGHT, MAX_ITEM_WEIGHT);
        if (itemWeightRange.contains(itemWeight)) {
            return;
        }
        throw new APIException("Item weight must be in range from " + MIN_ITEM_WEIGHT + " to " + MAX_ITEM_WEIGHT);
    }

    private static void checkItemCost(final double itemCost) throws APIException {
        final Range<Double> itemCostRange = Range.between(MIN_ITEM_COST, MAX_ITEM_COST);
        if (itemCostRange.contains(itemCost)) {
            return;
        }
        throw new APIException("Item cost must be in range from " + MIN_ITEM_COST + " to " + MAX_ITEM_COST);
    }

    private static void checkItemPackageCount(final int itemsCount) throws APIException {
        final Range<Integer> itemPackageCountRange = Range.between(POSITIVE_INDEX, MAX_ITEMS_IN_PACKAGE);
        if (itemPackageCountRange.contains(itemsCount)) {
            return;
        }
        throw new APIException("Items count must be in range from " + POSITIVE_INDEX + " to " + MAX_ITEMS_IN_PACKAGE);
    }

    private static void checkItemPackageWeight(final int packageWeight) throws APIException {
        final Range<Integer> itemPackageWeightRange = Range.between(POSITIVE_INDEX, MAX_PACKAGE_WEIGHT);
        if (itemPackageWeightRange.contains(packageWeight)) {
            return;
        }
        throw new APIException("Package weight must be in range from " + POSITIVE_INDEX + " to " + MAX_PACKAGE_WEIGHT);
    }
}
