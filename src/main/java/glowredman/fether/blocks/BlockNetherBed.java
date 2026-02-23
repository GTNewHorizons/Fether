package glowredman.fether.blocks;

import java.util.Random;

import net.minecraft.block.BlockBed;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import glowredman.fether.FetherBlocks;
import glowredman.fether.FetherItems;

public class BlockNetherBed extends BlockBed {

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (worldIn.isRemote) {
            return true;
        }

        int meta = worldIn.getBlockMetadata(x, y, z);

        if (!isBlockHeadOfBed(meta)) {
            int dir = getDirection(meta);
            x += field_149981_a[dir][0]; // MCP: bedDirections
            z += field_149981_a[dir][1]; // MCP: bedDirections

            if (worldIn.getBlock(x, y, z) != this) {
                return true;
            }

            meta = worldIn.getBlockMetadata(x, y, z);
        }

        // MCP: isBedOccupied()
        if (func_149976_c(meta)) {
            EntityPlayer currentPlayerInBed = null;

            for (EntityPlayer tempPlayer : worldIn.playerEntities) {
                if (tempPlayer.isPlayerSleeping()) {
                    ChunkCoordinates posPlayer2 = tempPlayer.playerLocation;
                    if (posPlayer2.posX == x && posPlayer2.posY == y && posPlayer2.posZ == z) {
                        currentPlayerInBed = tempPlayer;
                    }
                }
            }

            if (currentPlayerInBed != null) {
                player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied"));
                return true;
            }

            // MCP: setBedOccupied
            func_149979_a(worldIn, x, y, z, false);
        }

        EntityPlayer.EnumStatus status = player.sleepInBedAt(x, y, z);

        switch (status) {
            case NOT_POSSIBLE_HERE -> {
                player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notHere"));
            }
            case NOT_POSSIBLE_NOW -> {
                player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.noSleep"));
            }
            case NOT_SAFE -> {
                player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notSafe"));
            }
            case OK -> {
                // MCP: setBedOccupied
                func_149979_a(worldIn, x, y, z, true);
            }
            default -> { /* do nothing */ }
        }

        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0) {
            return FetherBlocks.blockNetherPlanks.getBlockTextureFromSide(side);
        }
        return super.getIcon(side, meta);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return isBlockHeadOfBed(meta) ? Item.getItemById(0) : FetherItems.itemNetherBed;
    }

    @Override
    public Item getItem(World worldIn, int x, int y, int z) {
        return FetherItems.itemNetherBed;
    }

    @Override
    public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
        return true;
    }
}
