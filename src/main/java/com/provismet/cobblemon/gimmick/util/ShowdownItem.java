package com.provismet.cobblemon.gimmick.util;

import com.mojang.serialization.Codec;

public final class ShowdownItem {

    private final String showdownItem;

    public ShowdownItem(String showdownItem) {
        this.showdownItem = showdownItem;
    }

    public String getShowdownItem() {
        return showdownItem;
    }

    // Exact equivalent of the Kotlin companion object
    public static final Codec<ShowdownItem> CODEC =
            Codec.STRING.xmap(
                    ShowdownItem::new,
                    ShowdownItem::getShowdownItem
            );

    @Override
    public String toString() {
        return "ShowdownItem(" + showdownItem + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowdownItem)) return false;
        ShowdownItem that = (ShowdownItem) o;
        return showdownItem.equals(that.showdownItem);
    }

    @Override
    public int hashCode() {
        return showdownItem.hashCode();
    }
}