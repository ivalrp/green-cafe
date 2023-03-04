package org.ival.model.constant;

public enum ItemCategory {

    A_LA_CARTE("a la carte"),
    APPERTIZERS("appertizers"),
    SIDES("sides"),
    EXTRAS("extras"),
    BEVERAGES("beverages");

    private String name;

    public String getName() {
        return name;
    }
    ItemCategory(String name) {
        this.name = name;
    }
}
