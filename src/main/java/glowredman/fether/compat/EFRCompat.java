package glowredman.fether.compat;

import ganymedes01.etfuturum.api.StrippedLogRegistry;
import glowredman.fether.FetherBlocks;

public class EFRCompat {

    public static void init() {
        StrippedLogRegistry.addLog(FetherBlocks.blockNetherLog, 0, FetherBlocks.blockNetherLog, 2);
        StrippedLogRegistry.addLog(FetherBlocks.blockNetherLog, 1, FetherBlocks.blockNetherLog, 3);
    }
}
