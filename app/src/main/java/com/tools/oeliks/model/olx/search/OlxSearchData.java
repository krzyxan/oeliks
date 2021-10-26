package com.tools.oeliks.model.olx.search;

import com.tools.oeliks.model.olx.item.OlxItem;

import java.util.HashSet;

import lombok.Value;

@Value
public class OlxSearchData {

    String url;
    String description;

    HashSet<OlxItem> items = new HashSet<>();

    Integer availableNewItems = 0;

    /**
     * Compares previous items with new ones.
     *
     * @param items to compare with previous ones.
     * @return amount of new items available
     */
    public Integer compareAndResetItems(HashSet<OlxItem> items) {
        //TODO add comparing items

        return availableNewItems;
    }
}

