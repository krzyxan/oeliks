package com.tools.oeliks.model.olx.search;

import com.tools.oeliks.model.olx.search.item.OlxItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"url"})
public class OlxSearchData implements Serializable {

    private final String url; //TODO add url check
    private final String description;

    private final HashSet<OlxItem> items = new HashSet<>();

    private final AtomicInteger availableNewItems = new AtomicInteger(0);

    public OlxSearchData(JSONObject json) throws JSONException {
        this.url = json.getString("url");
        this.description = json.getString("description");

        final JSONArray itemsArray = json.getJSONArray("items");
        for (int i = 0; i < itemsArray.length(); i++) {
            final JSONObject jsonObj = itemsArray.getJSONObject(i);
            this.items.add(new OlxItem(jsonObj));
        }
    }

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


    /**
     * Maps this object to JSONObject
     *
     * @return this as JSONObject
     */
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject json = new JSONObject();
        json.put("url", url);
        json.put("description", description);
        json.put("availableNewItems", availableNewItems);

        final JSONArray itemsArray = new JSONArray();
        for (OlxItem item : items) {
            itemsArray.put(item.toJSONObject());
        }

        json.put("items", itemsArray);
        return json;
    }
}

