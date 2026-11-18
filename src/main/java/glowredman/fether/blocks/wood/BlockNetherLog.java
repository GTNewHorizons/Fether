package glowredman.fether.blocks.wood;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import glowredman.fether.FetherItems;

public class BlockNetherLog extends BlockLogBase {

    public BlockNetherLog() {
        super("normal", "infested", "normal_stripped", "infested_stripped");
    }

    @Override
    public int damageDropped(int meta) {
        return meta & 2;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = super.getDrops(world, x, y, z, metadata, fortune);
        if ((metadata & 1) == 1) {
            Random rng = world.rand;
            int tries;
            if (fortune > 0) {
                tries = Math.max(rng.nextInt(fortune + 2), 1);
            } else {
                tries = 1;
            }
            for (int i = 0; i < tries; i++) {
                if (rng.nextBoolean()) {
                    list.add(new ItemStack(FetherItems.itemWeevil));
                } else {
                    list.add(new ItemStack(FetherItems.itemWeevilEgg));
                }
            }
        }
        return list;
    }
}
