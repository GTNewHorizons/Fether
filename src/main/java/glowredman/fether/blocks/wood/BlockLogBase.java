package glowredman.fether.blocks.wood;

import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.fether.blocks.AnimVariants;

public class BlockLogBase extends BlockLog {

    public static final String SPECIAL_SUFFIX = "_special";
    public static final String EYE_SUFFIX = "_eye";

    public final String[] names;
    @SideOnly(Side.CLIENT)
    private IIcon[][] eyeVariants;

    public BlockLogBase(String... names) {
        if (names.length > 4) {
            throw new IllegalArgumentException("BlockLog supports at most 4 variants, got " + names.length);
        }
        this.names = names;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < this.names.length; meta++) {
            list.add(new ItemStack(itemIn, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        IIcon icon = this.getIcon(side, meta);
        int type = (meta & 3) % this.names.length;
        if (this.eyeVariants[type] != null && icon == this.field_150167_a[type]) {
            int v = AnimVariants.pick(x, y, z);
            if (v > 0) {
                return this.eyeVariants[type][v];
            }
        }
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.eyeVariants = new IIcon[this.names.length][];
        this.field_150167_a = new IIcon[this.names.length];
        this.field_150166_b = new IIcon[this.names.length];

        for (int i = 0; i < this.names.length; i++) {
            String name = this.names[i];
            if (name.endsWith(SPECIAL_SUFFIX)) {
                String base = name.substring(0, name.length() - SPECIAL_SUFFIX.length());
                this.field_150167_a[i] = reg.registerIcon(this.getTextureName() + "_" + base);
                this.field_150166_b[i] = reg.registerIcon(this.getTextureName() + "_" + base + "_top" + SPECIAL_SUFFIX);
            } else if (name.endsWith(EYE_SUFFIX)) {
                String base = name.substring(0, name.length() - EYE_SUFFIX.length());
                this.field_150167_a[i] = reg.registerIcon(this.getTextureName() + "_" + name);
                this.field_150166_b[i] = reg.registerIcon(this.getTextureName() + "_" + base + "_top");
                this.eyeVariants[i] = new IIcon[AnimVariants.COUNT];
                for (int v = 1; v < AnimVariants.COUNT; v++) {
                    this.eyeVariants[i][v] = reg.registerIcon(this.getTextureName() + "_" + name + "_" + v);
                }
            } else {
                this.field_150167_a[i] = reg.registerIcon(this.getTextureName() + "_" + name);
                this.field_150166_b[i] = reg.registerIcon(this.getTextureName() + "_" + name + "_top");
            }
        }
    }
}
