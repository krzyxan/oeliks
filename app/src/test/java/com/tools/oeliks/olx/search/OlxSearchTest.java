package com.tools.oeliks.olx.search;

import com.tools.oeliks.model.olx.item.OlxItem;
import com.tools.oeliks.model.olx.search.OlxSearchData;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;


public class OlxSearchTest {

    @Test
    public void newAvailableItemsTest() {
        final HashSet<OlxItem> firstItems = new HashSet<>();
        firstItems.add(createOlxItem("Playstation 3"));
        firstItems.add(createOlxItem("Playstation 4"));

        final OlxSearchData olxSearch = new OlxSearchData("custom-url", "custom-description");
        final Integer firstCompareResult = olxSearch.compareAndResetItems(firstItems);
        Assertions.assertEquals(new Integer(2), firstCompareResult);
    }

    @Test
    public void newAvailableItemsRefresh() {
        final HashSet<OlxItem> firstItems = new HashSet<>();
        firstItems.add(createOlxItem("Playstation 3"));
        firstItems.add(createOlxItem("Playstation 4"));

        final HashSet<OlxItem> secondItems = new HashSet<>();
        secondItems.add(createOlxItem("Playstation 3"));
        secondItems.add(createOlxItem("Playstation 4"));
        secondItems.add(createOlxItem("Playstation 5"));

        final OlxSearchData olxSearch = new OlxSearchData("custom-url", "custom-description");
        final Integer firstCompareResult = olxSearch.compareAndResetItems(firstItems);
        Assertions.assertEquals(new Integer(2), firstCompareResult);


        final Integer secondCompareResult = olxSearch.compareAndResetItems(secondItems);
        Assertions.assertEquals(new Integer(1), secondCompareResult);
    }

    @Test
    public void lessAvailableItems() {
        final HashSet<OlxItem> firstItems = new HashSet<>();
        firstItems.add(createOlxItem("Playstation 3"));
        firstItems.add(createOlxItem("Playstation 4"));

        final HashSet<OlxItem> secondItems = new HashSet<>();
        secondItems.add(createOlxItem("Playstation 3"));

        final OlxSearchData olxSearch = new OlxSearchData("custom-url", "custom-description");
        final Integer firstCompareResult = olxSearch.compareAndResetItems(firstItems);
        Assertions.assertEquals(new Integer(2), firstCompareResult);


        final Integer secondCompareResult = olxSearch.compareAndResetItems(secondItems);
        Assertions.assertEquals(new Integer(0), secondCompareResult);
    }

    public OlxItem createOlxItem(String name) {
        return new OlxItem(name);
    }
}
