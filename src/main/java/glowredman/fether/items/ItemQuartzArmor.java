package glowredman.fether.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import glowredman.fether.Fether;
import glowredman.fether.FetherItems;

public class ItemQuartzArmor extends ItemArmor {

    /**
     * @param armorType 0 = helmet, 1 = chestplate, 2 = leggings, 3 = boots
     */
    public ItemQuartzArmor(int armorType) {
        super(FetherItems.ARMOR_MATERIAL_QUARTZ, 0, armorType);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (slot == 2) {
            return Fether.MODID + ":textures/models/armor/quartz_layer_2.png";
        }
        return Fether.MODID + ":textures/models/armor/quartz_layer_1.png";
    }
}
