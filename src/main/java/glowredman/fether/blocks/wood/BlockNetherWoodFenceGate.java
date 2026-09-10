package glowredman.fether.blocks.wood;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.util.IIcon;

import glowredman.fether.FetherBlocks;

public class BlockNetherWoodFenceGate extends BlockFenceGate {

    @Override
    public IIcon getIcon(int side, int meta) {
        return FetherBlocks.blockNetherWoodPlanks.getBlockTextureFromSide(side);
    }
}
