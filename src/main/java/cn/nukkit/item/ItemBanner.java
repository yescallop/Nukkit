package cn.nukkit.item;

import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntityBanner.Pattern;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.IntTag;
import cn.nukkit.nbt.tag.ListTag;

/**
 * @author Sandertv
 */
public class ItemBanner extends Item {

    public ItemBanner() {
        this(0);
    }

    public ItemBanner(Integer meta) {
        this(meta, 1);
    }

    public ItemBanner(Integer meta, int count) {
        super(BANNER, meta, count, "Banner");
        this.block = Block.get(Block.STANDING_BANNER);
    }

    @Override
    public int getMaxStackSize() {
        return 16;
    }

    /**
     * Returns the color of the banner base.
     *
     * @return int
     */
    public int getBaseColor() {
        return this.getNamedTag().getInt("Base");
    }

    /**
     * Sets the color of the banner base.
     */
    public void setBaseColor(int color) {
        this.getNamedTag().putInt("Base", color & 0x0f);
    }

    public int addPattern(Pattern pattern) {
        return addPattern(pattern.pattern, pattern.color);
    }

    /**
     * Applies a new pattern on the banner with the given color.
     *
     * @return int ID of pattern.
     */
    public int addPattern(String pattern, int color) {
        this.correctNBT();
        int patternId = this.getNamedTag().getList("Patterns").size();

        this.getNamedTag().getList("Patterns", CompoundTag.class).add(patternId, new CompoundTag()
                .putInt("Color", color & 0x0f)
                .putString("Pattern", pattern)
        );

        return patternId;
    }

    /**
     * Returns whether a pattern with the given ID exists on the banner or not.
     */
    public boolean patternExists(int patternId) {
        this.correctNBT();
        ListTag list = this.getNamedTag().getList("Patterns");

        return list.size() > patternId && list.get(patternId) != null;
    }

    /**
     * Returns the data of a pattern with the given ID.
     */
    public Pattern getPatternData(int patternId) {
        if (!this.patternExists(patternId)) {
            return null;
        }

        CompoundTag data = this.getNamedTag().getList("Patterns", CompoundTag.class).get(patternId);


        return new Pattern(data.getInt("Color"), data.getString("Pattern"));
    }

    /**
     * Changes the pattern of a previously existing pattern.
     *
     * @return bool indicating success.
     */
    public boolean changePattern(int patternId, String pattern, int color) {
        if (!this.patternExists(patternId)) {
            return false;
        }
        this.getNamedTag().getList("Patterns", CompoundTag.class).get(patternId)
                .putInt("Color", color & 0x0F)
                .putString("Pattern", pattern);

        return true;
    }

    /**
     * Deletes a pattern from the banner with the given ID.
     *
     * @return bool indicating whether the pattern existed or not.
     */
    public boolean deletePattern(int patternId) {
        if (!this.patternExists(patternId)) {
            return false;
        }
        this.getNamedTag().getList("Patterns").remove(patternId);

        return true;
    }

    /**
     * Deletes the top most pattern of the banner.
     *
     * @return bool indicating whether the banner was empty or not.
     */
    public boolean deleteTopPattern() {
        this.correctNBT();
        ListTag list = this.getNamedTag().getList("Patterns");

        if (list.size() <= 0) {
            return false;
        }

        list.remove(list.size() - 1);

        return true;
    }

    /**
     * Deletes the bottom pattern of the banner.
     *
     * @return bool indicating whether the banner was empty or not.
     */
    public boolean deleteBottomPattern() {
        this.correctNBT();
        ListTag list = this.getNamedTag().getList("Patterns");

        if (list.size() <= 0) {
            return false;
        }

        list.remove(0);
        return true;
    }

    /**
     * Returns the total count of patterns on this banner.
     *
     * @return int
     */
    public int getPatternCount() {
        this.correctNBT();
        return this.getNamedTag().getList("Patterns").size();
    }

    public void correctNBT() {
        CompoundTag tag = this.getNamedTag() != null ? this.getNamedTag() : new CompoundTag();

        if (!tag.contains("Base") || !(tag.get("Base") instanceof IntTag)) {
            tag.putInt("Base", this.meta);
        }

        if (!tag.contains("Patterns") || !(tag.get("Patterns") instanceof ListTag)) {
            tag.putList(new ListTag<CompoundTag>("Patterns"));
        }

        this.setNamedTag(tag);
    }
}
