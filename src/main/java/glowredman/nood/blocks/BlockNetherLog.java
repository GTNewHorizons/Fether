package glowredman.nood.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.nood.NoodItems;

public class BlockNetherLog extends BlockLog {

    public static final String[] NAMES = { "normal", "infested", "normal_stripped", "infested_stripped" };

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < NAMES.length; meta++) {
            list.add(new ItemStack(itemIn, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.field_150167_a = new IIcon[NAMES.length];
        this.field_150166_b = new IIcon[NAMES.length];

        for (int i = 0; i < NAMES.length; i++) {
            this.field_150167_a[i] = reg.registerIcon(this.getTextureName() + "_" + NAMES[i]);
            this.field_150166_b[i] = reg.registerIcon(this.getTextureName() + "_" + NAMES[i] + "_top");
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = super.getDrops(world, x, y, z, metadata, fortune);
        if (func_150165_c(metadata) == 1) {
            Random rng = world.rand;
            int tries;
            if (fortune > 0) {
                tries = Math.max(rng.nextInt(fortune + 2), 1);
            } else {
                tries = 1;
            }
            for (int i = 0; i < tries; i++) {
                if (rng.nextBoolean()) {
                    list.add(new ItemStack(NoodItems.itemWeevil));
                } else {
                    list.add(new ItemStack(NoodItems.itemWeevilEgg));
                }
            }
        }
        return list;
    }
}
