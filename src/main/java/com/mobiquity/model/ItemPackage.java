package com.mobiquity.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.mobiquity.exception.APIException;

/**
 * Representing domain model of item package with weight and list of {@link Item}
 *
 * @author igor.sila (isila)
 */
public class ItemPackage {

    private int maxWeight;
    private List<Item> items;

    /**
     * Item package constructor
     *
     * @param maxWeight the package maximum weight. Must be in range from 0 to 100
     * @param items     the package {@link Item} items
     * @throws {@link APIException} when parameters are used to construct the {@link ItemPackage} are invalid
     */
    public ItemPackage(final int maxWeight, final List<Item> items) throws APIException {
        if (Objects.isNull(items)) {
            throw new APIException("No items provided for package");
        }
        try {
            ItemValidator.validateItemPackage(items.size(), maxWeight);
            //this multiplying is done to relate multiplied weight in items
            this.maxWeight = maxWeight * 100;
            this.items = items;

            this.items.sort(Comparator.comparing(Item::getWeight)
                                      .thenComparing(Item::getCost));

        } catch (APIException e) {
            throw new APIException("Exception occurred during Item package creation", e);
        }
    }

    /**
     * Getter the maximum weight for current {@link ItemPackage}
     *
     * @return the maximum weight for current {@link ItemPackage}
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * Getter for the {@link Item} list
     *
     * @return the {@link Item} list as unmodifiable collection to prevent possible concurrency problems
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}
