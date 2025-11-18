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
 * Nectar items that change Oricorio's form.
 * - Pink Nectar → Pa'u Style (Psychic/Flying)
 * - Red Nectar → Baile Style (Fire/Flying)
 * - Yellow Nectar → Pom-Pom Style (Electric/Flying)
 * - Purple Nectar → Sensu Style (Ghost/Flying)
 */
public class OricorioNectarItem extends PolymerPokemonSelectingItem {
    private static final String FEATURE = "oricorio_form";
    private final String formName; // "pau", "baile", "pompom", "sensu"

    public OricorioNectarItem(Settings settings, Item baseVanillaItem, PolymerModelData modelData, String formName) {
        super(settings, baseVanillaItem, modelData, 1);
        this.formName = formName;
    }

    @Override
    public boolean canUseOnPokemon(@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:oricorio");
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon(@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(stack, pokemon)) {
            return TypedActionResult.fail(stack);
        }

        new StringSpeciesFeature(FEATURE, formName).apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.form", pokemon.getDisplayName(false), pokemon.getForm().getName()), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("oricorio_nectar_" + formName));
        }

        return TypedActionResult.success(stack);
    }
}
