package glowredman.fether.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import glowredman.fether.FetherConfig;
import glowredman.fether.FetherItems;

public class BlockNetherGarden extends BlockNetherFlower {

    public BlockNetherGarden() {
        super(FetherConfig.gardenSpreadRate);
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.netherrack || ground == Blocks.soul_sand;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return switch (random.nextInt(3)) {
            case 0 -> FetherConfig.gardensDropSeeds ? FetherItems.itemBloodLeafSeeds : FetherItems.itemBloodLeaf;
            case 1 -> FetherConfig.gardensDropSeeds ? FetherItems.itemFleshRootSeeds : FetherItems.itemFleshRoot;
            default -> FetherConfig.gardensDropSeeds ? FetherItems.itemMarrowBerrySeeds : FetherItems.itemMarrowBerry;
        };
    }

    @Override
    public int quantityDropped(Random random) {
        return FetherConfig.gardenDropAmount;
    }
}
