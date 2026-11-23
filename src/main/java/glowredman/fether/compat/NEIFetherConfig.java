package glowredman.fether.compat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import glowredman.fether.FetherBlocks;
import glowredman.fether.Tags;
import glowredman.fether.compat.FetherCompatHandler.Mods;

public class NEIFetherConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.hideItem(new ItemStack(FetherBlocks.blockDoubleNetherWoodSlab, 1, OreDictionary.WILDCARD_VALUE));
        if (!Mods.ET_FUTURUM_REQUIEM.isLoaded()) {
            API.hideItem(new ItemStack(FetherBlocks.blockNetherLog, 1, 2));
            API.hideItem(new ItemStack(FetherBlocks.blockNetherLog, 1, 3));
            API.hideItem(new ItemStack(FetherBlocks.blockLegacyNetherLog, 1, 2));
            API.hideItem(new ItemStack(FetherBlocks.blockLegacyNetherLog, 1, 3));
            API.hideItem(new ItemStack(FetherBlocks.blockWitchfireLog, 1, 2));
        }
    }

    @Override
    public String getName() {
        return "Fether";
    }

    @Override
    public String getVersion() {
        return Tags.VERSION;
    }

}
