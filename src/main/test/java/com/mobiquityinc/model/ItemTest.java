package com.mobiquityinc.model;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Item}
 *
 * @author igor.sila (isila)
 */

public class ItemTest {

    @Test
    public void create_valid_item() throws APIException {
        new Item(1, 50, 50);
    }

    @Test
    public void create_invalid_item__negative_index() {
        Assertions.assertThrows(APIException.class, () -> new Item(-1, 50, 50));
    }

    @Test
    public void create_invalid_item__too_big_weight() {
        Assertions.assertThrows(APIException.class, () -> new Item(1, 500, 50));
    }

    @Test
    public void create_invalid_item__too_big_cost() {
        Assertions.assertThrows(APIException.class, () -> new Item(1, 50, 500));
    }

    @Test
    public void create_invalid_item__negative_cost() {
        Assertions.assertThrows(APIException.class, () -> new Item(1, 50, -1));
    }

    @Test
    public void create_invalid_item__negative_weight() {
        Assertions.assertThrows(APIException.class, () -> new Item(1, -1, 50));
    }
}
