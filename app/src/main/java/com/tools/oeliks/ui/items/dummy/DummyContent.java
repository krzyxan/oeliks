package com.tools.oeliks.ui.items.dummy;

import com.tools.oeliks.model.olx.search.OlxSearchData;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<OlxSearchData> ITEMS = new ArrayList<>(); //TODO remove dummy content and add dynamic generated content


    private static final int COUNT = 8;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(OlxSearchData item) {
        ITEMS.add(item);
    }

    private static OlxSearchData createDummyItem(int val) {
        return new OlxSearchData("url", "ps5 v." + val);
    }

}