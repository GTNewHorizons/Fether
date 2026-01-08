package glowredman.nood.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import glowredman.nood.NoodConfig;
import glowredman.nood.NoodItems;

public class BlockNetherGarden extends BlockNetherFlower {

    public BlockNetherGarden() {
        super(NoodConfig.gardenSpreadRate);
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.netherrack || ground == Blocks.soul_sand;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return switch (random.nextInt(3)) {
            case 0 -> NoodConfig.gardensDropSeeds ? NoodItems.itemBloodLeafSeeds : NoodItems.itemBloodLeaf;
            case 1 -> NoodConfig.gardensDropSeeds ? NoodItems.itemFleshRootSeeds : NoodItems.itemFleshRoot;
            default -> NoodConfig.gardensDropSeeds ? NoodItems.itemMarrowBerrySeeds : NoodItems.itemMarrowBerry;
        };
    }

    @Override
    public int quantityDropped(Random random) {
        return NoodConfig.gardenDropAmount;
    }
}
