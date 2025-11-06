package com.provismet.cobblemon.gimmick.api.gimmick;

import com.provismet.cobblemon.gimmick.registry.GTGEnchantmentComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Utility interface for checking if an item should be considered a gimmick item.
 */
public interface GimmickCheck {
    static boolean isKeyStone(ItemStack item) {
        return isUnenchantedKeyStone(item)
                || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.KEY_STONE);
    }

    static boolean isZRing(ItemStack item) {
        return item.isIn(GTGItemTags.Z_RINGS)
                || item.get(GTGItemDataComponents.Z_RING) != null
                || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.Z_RING);
    }

    static boolean isDynamaxBand(ItemStack item) {
        return item.isIn(GTGItemTags.DYNAMAX_BANDS)
                || item.get(GTGItemDataComponents.DYNAMAX_BAND) != null
                || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.DYNAMAX_BAND);
    }

    static boolean isTeraOrb(ItemStack item) {
        if (item.isIn(GTGItemTags.BREAKABLE_TERA_ORBS) && item.isDamageable() && item.getDamage() == item.getMaxDamage())
            return false;

        return item.isIn(GTGItemTags.TERA_ORBS)
                || item.get(GTGItemDataComponents.TERA_ORB) != null
                || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.TERA_ORB);
    }

    static boolean isUnenchantedKeyStone(ItemStack item) {
        return item.isIn(GTGItemTags.KEY_STONES) || item.get(GTGItemDataComponents.KEY_STONE) != null;
    }

    // BEGIN FLOURISH MIGRATION

    static boolean isFlourishKeyStone(ItemStack flourishItem, ItemStack gtgItemDefaultStack) {
        // TODO > This checks GTG enchants for future enchanting, though I can't see if flourish has supported enchants
        return isFlourishUnenchantedKeyStone(gtgItemDefaultStack) || EnchantmentHelper.hasAnyEnchantmentsWith(flourishItem, GTGEnchantmentComponents.KEY_STONE);
    }

    static boolean isFlourishZRing(ItemStack flourishItem, ItemStack gtgItemDefaultStack) {
        return gtgItemDefaultStack.isIn(GTGItemTags.Z_RINGS)
                // Skipping data component check as the above will ensure it is in the right group
                || EnchantmentHelper.hasAnyEnchantmentsWith(flourishItem, GTGEnchantmentComponents.Z_RING);
    }

    static boolean isFlourishDynamaxBand(ItemStack flourishItem, ItemStack gtgItemDefaultStack) {
        return gtgItemDefaultStack.isIn(GTGItemTags.DYNAMAX_BANDS)
                // Skipping data component check as the above will ensure it is in the right group
                || EnchantmentHelper.hasAnyEnchantmentsWith(flourishItem, GTGEnchantmentComponents.DYNAMAX_BAND);
    }

    static boolean isFlourishTerraOrb(ItemStack flourishItem, ItemStack gtgItemDefaultStack) {
        if (gtgItemDefaultStack.isIn(GTGItemTags.BREAKABLE_TERA_ORBS) && flourishItem.isDamageable() && flourishItem.getDamage() == flourishItem.getMaxDamage())
            return false;

        return gtgItemDefaultStack.isIn(GTGItemTags.TERA_ORBS)
                // Skipping data component check as the above will ensure it is in the right group
                || EnchantmentHelper.hasAnyEnchantmentsWith(flourishItem, GTGEnchantmentComponents.TERA_ORB);
    }

    static boolean isFlourishUnenchantedKeyStone(ItemStack gtgItemDefaultStack) {
        return gtgItemDefaultStack.isIn(GTGItemTags.KEY_STONES);
    }
    // END FLOURISH MIGRATION
}
