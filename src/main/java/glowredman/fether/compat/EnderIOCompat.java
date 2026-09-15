package glowredman.fether.compat;

import crazypants.enderio.machine.farm.farmers.FarmersCommune;
import crazypants.enderio.machine.farm.farmers.TreeFarmer;
import glowredman.fether.FetherBlocks;

public class EnderIOCompat {

    public static void init() {
        FarmersCommune.joinCommune(new TreeFarmer(true, FetherBlocks.blockNetherSapling, FetherBlocks.blockNetherLog));
    }
}
