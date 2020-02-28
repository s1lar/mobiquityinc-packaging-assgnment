package com.mobiquity.model;

import com.mobiquity.exception.APIException;

/**
 * Representing domain model of Item with index, weight and cost as parameters
 *
 * @author igor.sila (isila)
 */
public class Item {

    private int index;
    private int weight;
    private double cost;

    /**
     * Item constructor
     *
     * @param index  the item index. Can not be negative
     * @param weight the item weight. Must be in range from 0 to 100
     * @param cost   the item cost. Must be in range from 0 to 100
     *
     * @throws {@link APIException} when parameters are used to construct the {@link Item} are invalid
     */
    public Item(final int index, final double weight, final double cost) throws APIException {
        try {
            ItemValidator.validateItem(index, weight, cost);
            this.index = index;
            //used for future algorithm (Knapsack), which works only with integers
            this.weight = (int) (weight * 100);
            this.cost = cost;
        } catch (APIException e) {
            throw new APIException("Exception occurred during Item creation", e);
        }
    }

    /**
     * Getter for item index.
     *
     * @return cost
     */
    public int getIndex() {
        return index;
    }

    /**
     * Getter for item weight.
     *
     * @return cost
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Getter for item cost.
     *
     * @return cost
     */
    public double getCost() {
        return cost;
    }
}
