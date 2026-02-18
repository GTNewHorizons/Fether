package glowredman.nood.compat;

import ganymedes01.etfuturum.api.StrippedLogRegistry;
import glowredman.nood.NoodBlocks;

public class EFRCompat {

    public static void init() {
        StrippedLogRegistry.addLog(NoodBlocks.blockNetherLog, 0, NoodBlocks.blockNetherLog, 2);
        StrippedLogRegistry.addLog(NoodBlocks.blockNetherLog, 1, NoodBlocks.blockNetherLog, 3);
    }
}
