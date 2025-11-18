package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Appliance items that change Rotom's form.
 * - Lawn Mower → rotom_mow (Grass)
 * - Microwave Oven → rotom_heat (Fire) or rotom_wash (Water)
 * - Refrigerator → rotom_frost (Ice)
 * - Electric Fan → rotom_fan (Flying)
 */
public class RotomApplianceItem extends PolymerPokemonSelectingItem {
    private static final String FEATURE = "appliance";
    private final String applianceForm; // "rotom_mow", "rotom_heat", "rotom_wash", "rotom_frost", "rotom_fan"

    public RotomApplianceItem(Settings settings, Item baseVanillaItem, PolymerModelData modelData, String applianceForm) {
        super(settings, baseVanillaItem, modelData, 1);
        this.applianceForm = applianceForm;
    }

    @Override
    public boolean canUseOnPokemon(@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:rotom");
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon(@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(stack, pokemon)) {
            return TypedActionResult.fail(stack);
        }

        new StringSpeciesFeature(FEATURE, applianceForm).apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.form", pokemon.getDisplayName(false), pokemon.getForm().getName()), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("rotom_appliance_" + applianceForm));
        }

        return TypedActionResult.success(stack);
    }
}
