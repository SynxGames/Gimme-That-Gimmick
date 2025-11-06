package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.data.component.FormToggle;
import com.provismet.cobblemon.gimmick.api.data.component.Fusion;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class DataDrivenFusionItem extends AbstractDataDrivenFormItem implements FormChangeFusionItem {
    public DataDrivenFusionItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    @Override
    public boolean canBeMerged (ItemStack stack, Pokemon other) {
        // BEGIN FLOURISH CHECK
        Item flourishItem = GTGItems.getFlourishItemType(stack);
        ItemStack stackToCheck = (flourishItem != null) ? flourishItem.getDefaultStack() : stack;
        Fusion fusionData = stackToCheck.get(GTGItemDataComponents.FUSION);
//        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        // END FLOURISH CHECK
        if (fusionData != null) {
            return fusionData.canBeUsedAsInput(other);
        }
        return false;
    }

    @Override
    public void applyUnplitForme (ItemStack stack, Pokemon pokemon) {
        // BEGIN FLOURISH CHECK
        Item flourishItem = GTGItems.getFlourishItemType(stack);
        ItemStack stackToCheck = (flourishItem != null) ? flourishItem.getDefaultStack() : stack;
        Fusion fusionData = stackToCheck.get(GTGItemDataComponents.FUSION);
//        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        // END FLOURISH CHECK
        if (fusionData != null) {
            fusionData.removeFeatures(pokemon);

            if (fusionData.unfusionEffect().isPresent()) {
                PokemonEntity entity = pokemon.getEntity();
                if (entity == null) return;

                EffectsData.run(entity, fusionData.unfusionEffect().get());
            }
        }
    }

    @Override
    public void applyFusedForme (ItemStack stack, Pokemon pokemon, Pokemon other) {
        // BEGIN FLOURISH CHECK
        Item flourishItem = GTGItems.getFlourishItemType(stack);
        ItemStack stackToCheck = (flourishItem != null) ? flourishItem.getDefaultStack() : stack;
        Fusion fusionData = stackToCheck.get(GTGItemDataComponents.FUSION);
//        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        // END FLOURISH CHECK
        if (fusionData != null) {
            fusionData.applyFeatures(pokemon, other);

            if (fusionData.fusionEffect().isPresent()) {
                PokemonEntity entity = pokemon.getEntity();
                if (entity == null) return;

                EffectsData.run(entity, fusionData.fusionEffect().get());
            }
        }
    }

    @Override
    public boolean canUseOnPokemon (ItemStack stack, Pokemon pokemon) {
        // BEGIN FLOURISH CHECK
        Item flourishItem = GTGItems.getFlourishItemType(stack);
        ItemStack stackToCheck = (flourishItem != null) ? flourishItem.getDefaultStack() : stack;
        Fusion fusionData = stackToCheck.get(GTGItemDataComponents.FUSION);
//        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        // END FLOURISH CHECK

        if (fusionData == null) return false;
        return fusionData.recipient().matches(pokemon);
    }
}
