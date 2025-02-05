package net.chung.newengine.tooltiers;

import net.chung.newengine.item.ModItems;
import net.chung.newengine.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers   {
    public static final Tier UNKNOWNRITE = new ForgeTier(4231, 15, 6f, 25,
            ModTags.Blocks.NEEDS_UNKNOWN_TOOLS, () -> Ingredient.of(ModItems.UNKNOWNRITE_INGOT.get()),
            ModTags.Blocks.INCORRECT_FOR_UNKNOWN_TOOLS);
}
