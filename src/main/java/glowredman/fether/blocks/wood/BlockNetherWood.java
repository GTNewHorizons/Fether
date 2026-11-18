package glowredman.fether.blocks.wood;

import java.util.List;

import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNetherWood extends BlockWood {

    public final String[] names;

    public BlockNetherWood(String... names) {
        this.names = names;
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < this.names.length; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.field_150095_b = new IIcon[this.names.length];
        for (int i = 0; i < this.field_150095_b.length; i++) {
            this.field_150095_b[i] = reg.registerIcon(this.getTextureName() + "_" + this.names[i]);
        }
    }
}
