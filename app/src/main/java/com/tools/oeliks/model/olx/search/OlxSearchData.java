package com.tools.oeliks.model.olx.search;

import com.tools.oeliks.model.olx.search.item.OlxItem;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Value;

@Value
public class OlxSearchData {

    String url; //TODO add url check
    String description;

    HashSet<OlxItem> items = new HashSet<>();

    AtomicInteger availableNewItems = new AtomicInteger(0);

    /**
     * Compares previous items with new ones.
     *
     * @param items to compare with previous ones.
     * @return amount of new items available
     */
    public Integer compareAndResetItems(HashSet<OlxItem> items) {
        this.items.retainAll(items); //removes all Objects from 'this.items' which are not in 'items'

        final int newItems = items.size() - this.items.size();
        availableNewItems.set(newItems);

        this.items.clear();
        this.items.addAll(items);

        return newItems;
    }
}

