package glowredman.fether.client;

public final class AnimVariants {

    private AnimVariants() {}

    public static int pick(int x, int y, int z, int max) {
        long h = x * 3129871L ^ z * 116129781L ^ y;
        h = h * h * 42317861L + h * 11L;
        return (int) ((h >> 16) & (max - 1));
    }
}
