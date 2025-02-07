package net.chung.newengine.armor;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class UnknownArmorEffects extends ArmorItem {

    Holder<ArmorMaterial> UNKNOWN_ARMOR = ModArmorMaterials.UNKNOWNRITE_ARMOR_MATERIAL;

    public UnknownArmorEffects(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if(!level.isClientSide() && hasFullSuitOfArmorOn(player) && hasPlayerCorrectArmorOn(UNKNOWN_ARMOR, player)) {
            enableFlyToPlayer(player);
        }
        if(!hasPlayerCorrectArmorOn(UNKNOWN_ARMOR, player) || !hasFullSuitOfArmorOn(player)) {
            disableFlyToPlayer(player);
        }
    }


    private void enableFlyToPlayer(Player player) {
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();

    }

    private void disableFlyToPlayer(Player player) {
        player.getAbilities().flying = false;
        player.getAbilities().mayfly = false;
        player.onUpdateAbilities();
    }

    private boolean hasPlayerCorrectArmorOn(Holder<ArmorMaterial> armorMaterial, Player player) {
        for(ItemStack armorStack : player.getArmorSlots()) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return boots.getMaterial() == armorMaterial && leggings.getMaterial() == armorMaterial
                && chestplate.getMaterial() == armorMaterial && helmet.getMaterial() == armorMaterial;
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }

}