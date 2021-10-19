package com.tools.oeliks.model.olx;

import java.math.BigDecimal;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class OlxItem {
    String name;
    BigDecimal price;
}
