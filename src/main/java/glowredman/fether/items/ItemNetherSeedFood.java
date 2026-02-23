package glowredman.fether.items;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

import glowredman.fether.FetherConfig;

public class ItemNetherSeedFood extends ItemSeedFood {

    public ItemNetherSeedFood(@Nonnull Block blockCrop) {
        super(FetherConfig.foodHungerRestore, FetherConfig.foodSaturationModifier, blockCrop, Blocks.soul_sand);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Nether;
    }
}
