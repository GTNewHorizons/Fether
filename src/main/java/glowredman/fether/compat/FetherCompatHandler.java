package glowredman.fether.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;

public class FetherCompatHandler {

    public enum Mods {

        ENDERIO("EnderIO"),
        ET_FUTURUM_REQUIEM("etfuturum"),
        FORESTRY("Forestry"),
        MINEFACTORY_RELOADED("MineFactoryReloaded"),
        THAUMCRAFT("Thaumcraft"),
        TX_LOADER("txloader");

        private final String modID;
        private boolean isLoaded;

        private Mods(String modID) {
            this.modID = modID;
        }

        public boolean isLoaded() {
            return this.isLoaded;
        }

        public static void checkLoaded() {
            for (Mods mod : values()) {
                mod.isLoaded = Loader.isModLoaded(mod.modID);
            }
        }
    }

    public static void preInit(Side side) {
        if (side.isClient()) {
            if (Mods.TX_LOADER.isLoaded()) {
                TXLoaderCompat.init();
            }
        }
        if (Mods.FORESTRY.isLoaded()) {
            ForestryCompat.preInit();
        }
    }

    public static void init(Side side) {
        if (Mods.ENDERIO.isLoaded()) {
            EnderIOCompat.init();
        }
        if (Mods.ET_FUTURUM_REQUIEM.isLoaded()) {
            EFRCompat.init();
        }
        if (Mods.FORESTRY.isLoaded()) {
            ForestryCompat.init();
        }
        if (Mods.MINEFACTORY_RELOADED.isLoaded()) {
            MFRCompat.init();
        }
        if (Mods.THAUMCRAFT.isLoaded()) {
            ThaumcraftCompat.init();
        }
    }
}
