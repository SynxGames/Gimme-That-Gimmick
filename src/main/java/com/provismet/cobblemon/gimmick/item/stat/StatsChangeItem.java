package com.provismet.cobblemon.gimmick.item.stat;

import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;

/**
 * A held item that modifies Pokemon stats or battle mechanics.
 * All battle logic is handled by Pokemon Showdown - this class just holds the item
 * and maps it to a Showdown ID via CobblemonHeldItemManager.registerRemap().
 *
 * Usage: Register with registerShowdownItem("showdownid") in GTGItems
 *
 * Example Showdown items:
 * - "luckypunch" - Raises Chansey's crit ratio by 2 stages
 * - "thickclub" - Doubles Attack for Cubone/Marowak
 * - "oddincense" - Boosts Psychic-type moves by 1.2x
 * - "luminousmoss" - Raises Sp.Def by 1 when hit by Water move
 * - "deepseatooth" - Doubles Sp.Atk for Clamperl
 */
public class StatsChangeItem extends PolymerHeldItem {

    public StatsChangeItem(Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    public StatsChangeItem(Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines) {
        super(settings, baseVanillaItem, modelData, tooltipLines);
    }
}
