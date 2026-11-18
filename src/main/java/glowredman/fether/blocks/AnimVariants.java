package glowredman.fether.blocks;

// animations have 4 copies with different starts in loop, uses position to pick same copy, neighboring leaves and eyes
// blink at diff times
public final class AnimVariants {

    public static final int COUNT = 4;

    private AnimVariants() {}

    public static int pick(int x, int y, int z) {
        long h = x * 3129871L ^ z * 116129781L ^ y;
        h = h * h * 42317861L + h * 11L;
        return (int) ((h >> 16) & (COUNT - 1));
    }
}
