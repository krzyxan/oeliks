package com.tools.oeliks.model.olx.search.item;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
@EqualsAndHashCode(of = {"name"})
public class OlxItem {
    String name;
}
