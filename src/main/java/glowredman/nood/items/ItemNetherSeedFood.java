package glowredman.nood.items;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

import glowredman.nood.NoodConfig;

public class ItemNetherSeedFood extends ItemSeedFood {

    public ItemNetherSeedFood(@Nonnull Block blockCrop) {
        super(NoodConfig.foodHungerRestore, NoodConfig.foodSaturationModifier, blockCrop, Blocks.soul_sand);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Nether;
    }
}
