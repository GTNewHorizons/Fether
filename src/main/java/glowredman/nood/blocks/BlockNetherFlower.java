package glowredman.nood.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnegative;

import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNetherFlower extends BlockFlower {

    private final int spreadRate;

    protected BlockNetherFlower(@Nonnegative int spreadRate) {
        super(0);
        this.setBlockBounds(0.3f, 0.0f, 0.3f, 0.7f, 0.6f, 0.7f);
        this.setStepSound(soundTypeGrass);
        this.setTickRandomly(true);
        this.spreadRate = spreadRate;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon(this.getTextureName());
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn));
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (!worldIn.isRemote) {
            this.dropBlockAsItem(worldIn, x, y, z, new ItemStack(this));
            worldIn.setBlockToAir(x, y, z);
        }
        return false;
    }

    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        // drop as item if placement is no longer valid
        super.updateTick(worldIn, x, y, z, random);

        if (this.spreadRate < 1 || random.nextInt(this.spreadRate) != 0) {
            return;
        }

        int numThisPlant = 0;
        for (int testX = x - 4; testX <= x + 4; testX++) {
            for (int testY = y - 1; testY <= y + 1; testY++) {
                for (int testZ = z - 4; testZ <= z + 4; testZ++) {
                    if (worldIn.getBlock(testX, testY, testZ) == this) {
                        if (numThisPlant == 4) {
                            // spreading only occurs if there are less than 5 plants of this type in a 9x3x9 cuboid
                            // centered at (x,y,z)
                            return;
                        }
                        numThisPlant++;
                    }
                }
            }
        }

        int newX = x + random.nextInt(3) - 1;
        int newY = y + random.nextInt(1) - random.nextInt(1);
        int newZ = x + random.nextInt(3) - 1;
        for (int i = 0; i < 4; i++) {
            if (worldIn.isAirBlock(newX, newY, newZ) && this.canBlockStay(worldIn, newX, newY, newZ)) {
                x = newX;
                y = newY;
                z = newZ;
            }
            newX = x + random.nextInt(3) - 1;
            newY = y + random.nextInt(1) - random.nextInt(1);
            newZ = x + random.nextInt(3) - 1;
        }
        if (worldIn.isAirBlock(newX, newY, newZ) && this.canBlockStay(worldIn, newX, newY, newZ)) {
            worldIn.setBlock(newX, y, z, this, 0, 3);
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Nether;
    }
}
