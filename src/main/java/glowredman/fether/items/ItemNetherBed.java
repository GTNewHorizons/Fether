package glowredman.fether.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNetherBed extends Item {

    private final Block[] bedBlocks;
    private IIcon[] icons;

    public ItemNetherBed(Block... bedBlocks) {
        this.bedBlocks = bedBlocks;
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName() {
        return this.getBedBlock(0)
            .getUnlocalizedName();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getBedBlock(stack.getItemDamage())
            .getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < this.bedBlocks.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta < 0 || meta >= this.icons.length) {
            meta = 0;
        }
        return this.icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.icons = new IIcon[this.bedBlocks.length];
        for (int i = 0; i < this.icons.length; i++) {
            this.icons[i] = register.registerIcon(this.getIconString() + "_" + i);
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        if (side != 1) {
            return false;
        }
        y++;
        int rotation = MathHelper.floor_double(player.rotationYaw / 90.0 + 0.5) & 3;
        int offsetX = Direction.offsetX[rotation];
        int offsetZ = Direction.offsetZ[rotation];
        Block bedBlock = this.getBedBlock(stack.getItemDamage());

        if (player.canPlayerEdit(x, y, z, side, stack)
            && player.canPlayerEdit(x + offsetX, y, z + offsetZ, side, stack)) {
            if (world.isAirBlock(x, y, z) && world.isAirBlock(x + offsetX, y, z + offsetZ)
                && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)
                && World.doesBlockHaveSolidTopSurface(world, x + offsetX, y - 1, z + offsetZ)) {
                world.setBlock(x, y, z, bedBlock, rotation, 3);

                if (world.getBlock(x, y, z) == bedBlock) {
                    world.setBlock(x + offsetX, y, z + offsetZ, bedBlock, rotation + 8, 3);
                }

                stack.stackSize--;
                return true;
            }
        }
        return false;
    }

    private Block getBedBlock(int meta) {
        if (meta < 0 || meta >= this.bedBlocks.length) {
            meta = 0;
        }
        return this.bedBlocks[meta];
    }
}
