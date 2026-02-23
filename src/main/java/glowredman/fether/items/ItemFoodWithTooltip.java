package glowredman.fether.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemFoodWithTooltip extends ItemFood {

    public ItemFoodWithTooltip(int healAmount, boolean isWolfsFavoriteMeat) {
        super(healAmount, isWolfsFavoriteMeat);
    }

    public ItemFoodWithTooltip(int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
        super(healAmount, saturationModifier, isWolfsFavoriteMeat);
    }

    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean advancedTooltips) {
        list.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc"));
    }
}
