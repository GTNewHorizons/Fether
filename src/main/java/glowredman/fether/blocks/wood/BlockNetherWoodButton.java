package glowredman.fether.blocks.wood;

import net.minecraft.block.BlockButtonWood;
import net.minecraft.util.IIcon;

import glowredman.fether.FetherBlocks;

public class BlockNetherWoodButton extends BlockButtonWood {

    public BlockNetherWoodButton() {}

    @Override
    public IIcon getIcon(int side, int meta) {
        return FetherBlocks.blockNetherWoodPlanks.getBlockTextureFromSide(1);
    }
}
