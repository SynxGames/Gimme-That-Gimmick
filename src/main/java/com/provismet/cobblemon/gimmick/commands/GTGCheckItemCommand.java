package com.provismet.cobblemon.gimmick.commands;

import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class GTGCheckItemCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("gimme-that-gimmick")
                .then(CommandManager.literal("checkitem")
                    .executes(context -> {
                        ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                        ItemStack heldItem = player.getMainHandStack();

                        if (heldItem.isEmpty()) {
                            context.getSource().sendFeedback(() ->
                                Text.literal("You are not holding any item!").formatted(Formatting.RED),
                                false
                            );
                            return 0;
                        }

                        PolymerHeldItem detectedItem = GTGItems.getFlourishItemType(heldItem);

                        if (detectedItem != null) {
                            context.getSource().sendFeedback(() ->
                                Text.literal("Item detected: ").formatted(Formatting.GREEN)
                                    .append(Text.literal(detectedItem.toString()).formatted(Formatting.YELLOW)),
                                false
                            );
                            return 1;
                        } else {
                            context.getSource().sendFeedback(() ->
                                Text.literal("No Flourish item data detected on this item.").formatted(Formatting.YELLOW),
                                false
                            );
                            return 0;
                        }
                    })
                )
            );
        });
    }
}
