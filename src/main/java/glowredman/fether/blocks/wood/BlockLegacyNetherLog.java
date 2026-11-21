package glowredman.fether.blocks.wood;

import cpw.mods.fml.common.Loader;

public class BlockLegacyNetherLog extends BlockLogBase {

    public BlockLegacyNetherLog() {
        super("normal", "carved", "normal_stripped", "carved_stripped");
        if (Loader.isModLoaded("txloader")) {
            this.setStepSound(SoundTypeLegacyNetherLog.INSTANCE);
        }
    }

    protected static class SoundTypeLegacyNetherLog extends SoundType {

        public static final SoundType INSTANCE = new SoundTypeLegacyNetherLog();

        private SoundTypeLegacyNetherLog() {
            super("legacy_nether_log", 1.0f, 1.0f);
        }

        @Override
        public String getBreakSound() {
            return "fether:dig.legacy_nether_log";
        }

        @Override
        public String getStepResourcePath() {
            return "step.wood";
        }
    }
}
