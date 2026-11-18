package glowredman.fether.worldgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;

import glowredman.fether.Fether;

public final class TreeTemplate {

    public static final class Entry {

        public final int dx, dy, dz;
        public final Block block;
        public final int meta;

        public Entry(int dx, int dy, int dz, Block block, int meta) {
            this.dx = dx;
            this.dy = dy;
            this.dz = dz;
            this.block = block;
            this.meta = meta;
        }
    }

    public final String name;
    public final List<Entry> entries;
    public final int minY, maxY;

    public TreeTemplate(String name, List<Entry> entries) {
        this.name = name;
        this.entries = Collections.unmodifiableList(entries);
        int lo = 0, hi = 0;
        for (Entry e : entries) {
            lo = Math.min(lo, e.dy);
            hi = Math.max(hi, e.dy);
        }
        this.minY = lo;
        this.maxY = hi;
    }

    public static TreeTemplate read(String name, BufferedReader reader) throws IOException {
        List<Entry> list = new ArrayList<>();
        String line;
        int lineNo = 0;
        while ((line = reader.readLine()) != null) {
            lineNo++;
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }
            String[] p = line.split("\\s+");
            if (p.length < 5) {
                Fether.LOGGER.warn("Tree template {}:{} malformed: '{}'", name, lineNo, line);
                continue;
            }
            Block block = Block.getBlockFromName(p[3]);
            if (block == null) {
                Fether.LOGGER.warn("Tree template {}:{} unknown block '{}'", name, lineNo, p[3]);
                continue;
            }
            list.add(
                new Entry(
                    Integer.parseInt(p[0]),
                    Integer.parseInt(p[1]),
                    Integer.parseInt(p[2]),
                    block,
                    Integer.parseInt(p[4])));
        }
        return new TreeTemplate(name, list);
    }

    public void write(File file) throws IOException {
        File dir = file.getParentFile();
        if (dir != null && !dir.isDirectory() && !dir.mkdirs()) {
            throw new IOException("Could not create " + dir);
        }
        try (PrintWriter out = new PrintWriter(file, "UTF-8")) {
            out.println("# " + this.name + ", " + this.entries.size() + " blocks");
            for (Entry e : this.entries) {
                out.println(
                    e.dx + " "
                        + e.dy
                        + " "
                        + e.dz
                        + " "
                        + Block.blockRegistry.getNameForObject(e.block)
                        + " "
                        + e.meta);
            }
        }
    }

    private static final Map<String, List<TreeTemplate>> CACHE = new HashMap<>();
    public static File devExportDir;

    public static synchronized void clearCache() {
        CACHE.clear();
    }

    public static synchronized List<TreeTemplate> getSet(String set) {
        return CACHE.computeIfAbsent(set, TreeTemplate::loadSet);
    }

    private static List<TreeTemplate> loadSet(String set) {
        List<TreeTemplate> result = new ArrayList<>();
        tryLoad(set, result);
        for (int i = 1; i < 64; i++) {
            if (!tryLoad(set + "_" + i, result)) {
                break;
            }
        }
        if (result.isEmpty()) {
            Fether.LOGGER.warn("No tree templates found for set '{}'", set);
        }
        return result;
    }

    private static boolean tryLoad(String name, List<TreeTemplate> out) {
        if (devExportDir != null) {
            File f = new File(devExportDir, name + ".txt");
            if (f.isFile()) {
                try (BufferedReader r = new BufferedReader(
                    new InputStreamReader(new java.io.FileInputStream(f), StandardCharsets.UTF_8))) {
                    out.add(read(name, r));
                    return true;
                } catch (IOException e) {
                    Fether.LOGGER.error("Failed to read tree template " + f, e);
                }
            }
        }
        InputStream in = TreeTemplate.class.getResourceAsStream("/assets/fether/trees/" + name + ".txt");
        if (in == null) {
            return false;
        }
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            out.add(read(name, r));
            return true;
        } catch (IOException e) {
            Fether.LOGGER.error("Failed to read tree template " + name, e);
            return false;
        }
    }
}
