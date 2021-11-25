package com.tools.oeliks.model.olx.search.item;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class OlxItem implements Serializable {
    private final String name;

    public OlxItem(JSONObject json) throws JSONException {
        this.name = json.getString("name");
    }

    /**
     * Maps this object to JSONObject
     *
     * @return this as JSONObject
     */
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
