package glowredman.fether.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import glowredman.fether.FetherBlocks;

public class ItemNetherBed extends Item {

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

        if (player.canPlayerEdit(x, y, z, side, stack)
            && player.canPlayerEdit(x + offsetX, y, z + offsetZ, side, stack)) {
            if (world.isAirBlock(x, y, z) && world.isAirBlock(x + offsetX, y, z + offsetZ)
                && World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)
                && World.doesBlockHaveSolidTopSurface(world, x + offsetX, y - 1, z + offsetZ)) {
                world.setBlock(x, y, z, FetherBlocks.blockNetherBed, rotation, 3);

                if (world.getBlock(x, y, z) == FetherBlocks.blockNetherBed) {
                    world.setBlock(x + offsetX, y, z + offsetZ, FetherBlocks.blockNetherBed, rotation + 8, 3);
                }

                stack.stackSize--;
                return true;
            }
        }
        return false;
    }
}
