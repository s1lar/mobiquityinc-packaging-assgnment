package com.mobiquityinc.model;

import java.util.List;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ItemPackage}
 *
 * @author igor.sila (isila)
 */
public class ItemPackageTest {

    private Item item;

    @BeforeEach
    public void setupThis() throws APIException {
        this.item = new Item(1, 20, 50);
    }

    @Test
    public void create_valid_itemPackage() throws APIException {
        new ItemPackage(60, List.of(item));
    }

    @Test
    public void create_invalid_itemPackage__negative_package_weight() {
        Assertions.assertThrows(APIException.class, () -> new ItemPackage(-1, List.of(item)));
    }

    @Test
    public void create_invalid_itemPackage__too_big_package_weight() {
        Assertions.assertThrows(APIException.class, () -> new ItemPackage(101, List.of(item)));
    }

    @Test
    public void create_invalid_itemPackage__null_items() {
        Assertions.assertThrows(APIException.class, () -> new ItemPackage(95, null));
    }

    @Test
    public void create_invalid_itemPackage__too_many_items() {
        Assertions.assertThrows(APIException.class, () -> new ItemPackage(95,
                                                                          List.of(item, item, item, item,
                                                                                  item, item, item, item,
                                                                                  item, item, item, item,
                                                                                  item, item, item, item)));
    }
}
