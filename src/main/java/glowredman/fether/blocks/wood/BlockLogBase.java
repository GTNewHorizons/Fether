package glowredman.fether.blocks.wood;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import glowredman.fether.AnimVariants;

public class BlockLogBase extends BlockLog {

    public final String[] names;
    protected final int maxMeta;
    private final int[][] variationCount;
    private IIcon[][][] icons;

    public BlockLogBase(String... names) {
        if (names.length > 4) {
            throw new IllegalArgumentException("BlockLog supports at most 4 variants, got " + names.length);
        }
        this.names = names;
        this.maxMeta = names.length - 1;
        this.variationCount = new int[2][this.names.length];
        Arrays.fill(this.variationCount[0], 1);
        Arrays.fill(this.variationCount[1], 1);
    }

    public BlockLogBase setIconVariationCount(String name, boolean top, int count) {
        for (int i = 0; i < this.names.length; i++) {
            if (this.names[i].equals(name)) {
                this.variationCount[top ? 1 : 0][i] = count;
                break;
            }
        }
        return this;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int meta = 0; meta < this.names.length; meta++) {
            list.add(new ItemStack(itemIn, 1, meta));
        }
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        int logVar = MathHelper.clamp_int(meta & 0b0011, 0, this.maxMeta);
        int axis = meta >> 2;
        boolean isTop = axis == 0 && (side == 0 || side == 1) || axis == 1 && (side == 4 || side == 5)
            || axis == 2 && (side == 2 || side == 3);
        int numIconVars = this.variationCount[isTop ? 1 : 0][logVar];
        int iconVar = numIconVars > 1 ? AnimVariants.pick(x, y, z, numIconVars) : 0;
        return this.icons[isTop ? 1 : 0][logVar][iconVar];
    }

    @Override
    protected IIcon getSideIcon(int p_150163_1_) {
        return this.icons[0][MathHelper.clamp_int(p_150163_1_ & 0b0011, 0, this.maxMeta)][0];
    }

    @Override
    protected IIcon getTopIcon(int p_150163_1_) {
        return this.icons[1][MathHelper.clamp_int(p_150163_1_ & 0b0011, 0, this.maxMeta)][0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        int maxVars = Math.max(
            Arrays.stream(this.variationCount[0])
                .max()
                .getAsInt(),
            Arrays.stream(this.variationCount[1])
                .max()
                .getAsInt());
        this.icons = new IIcon[2][this.maxMeta + 1][maxVars];

        for (int i = 0; i <= this.maxMeta; i++) {
            if (this.variationCount[0][i] > 1) {
                for (int v = 0; v < this.variationCount[0][i]; v++) {
                    this.icons[0][i][v] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_" + v);
                }
                continue;
            }
            this.icons[0][i][0] = reg.registerIcon(this.getTextureName() + "_" + this.names[i]);
        }

        for (int i = 0; i <= this.maxMeta; i++) {
            if (this.variationCount[1][i] > 1) {
                for (int v = 0; v < this.variationCount[1][i]; v++) {
                    this.icons[1][i][v] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_top_" + v);
                }
                continue;
            }
            this.icons[1][i][0] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_top");
        }
    }
}
