package glowredman.nood.items;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class ItemNetherSeeds extends ItemSeeds {

    public ItemNetherSeeds(@Nonnull Block cropBlock) {
        super(cropBlock, Blocks.soul_sand);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Nether;
    }
}
