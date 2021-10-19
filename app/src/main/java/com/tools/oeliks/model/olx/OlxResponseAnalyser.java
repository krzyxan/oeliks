package com.tools.oeliks.model.olx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OlxResponseAnalyser {

    public static List<OlxItem> parseItems(String bodyData) {
        final List<OlxItem> items = new ArrayList<>();

        final String[] splitData = bodyData.split("<tbody>");

        if (splitData.length > 1) {
            for (int i = 1; i < splitData.length; i++) {
                final String[] item = splitData[i].split("<strong>");
                if (item.length > 2) {
                    final String name = item[1].split("</strong>")[0];
                    final String price = item[2].split(" zł")[0];
                    OlxItem olxItem = new OlxItem(name, new BigDecimal(price));
                    items.add(olxItem);
                }
            }
        }

        return items;
    }

}
