package glowredman.fether.blocks.wood;

import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLogBase extends BlockLog {

    public final String[] names;

    public BlockLogBase(String... names) {
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
    public void registerBlockIcons(IIconRegister reg) {
        this.field_150167_a = new IIcon[this.names.length];
        this.field_150166_b = new IIcon[this.names.length];

        for (int i = 0; i < this.names.length; i++) {
            this.field_150167_a[i] = reg.registerIcon(this.getTextureName() + "_" + this.names[i]);
            this.field_150166_b[i] = reg.registerIcon(this.getTextureName() + "_" + this.names[i] + "_top");
        }
    }
}
