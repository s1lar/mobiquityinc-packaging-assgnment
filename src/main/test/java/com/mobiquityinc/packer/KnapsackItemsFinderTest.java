package com.mobiquityinc.packer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemPackage;
import com.mobiquity.packer.KnapsackItemsFinder;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link KnapsackItemsFinder}
 *
 * @author igor.sila (isila)
 */
public class KnapsackItemsFinderTest {

    @Test
    public void find_valid_items() throws APIException {
        final List<Item> items = new ArrayList<>();
        items.add(new Item(1, 20, 50));
        items.add(new Item(2, 60, 50));
        items.add(new Item(3, 25, 50));

        final ItemPackage itemPackage = new ItemPackage(50, items);

        String itemIndexes = KnapsackItemsFinder.findItems(itemPackage);

        assertEquals(itemIndexes, "1,3");
    }

    @Test
    public void find_valid_items__no_valid_items() throws APIException {
        final List<Item> items = new ArrayList<>();
        items.add(new Item(1, 60, 50));

        final ItemPackage itemPackage = new ItemPackage(50, items);

        String itemIndexes = KnapsackItemsFinder.findItems(itemPackage);

        assertEquals(itemIndexes, "-");
    }

}
