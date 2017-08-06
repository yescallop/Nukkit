package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityShulkerBox;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.StringTag;
import cn.nukkit.nbt.tag.Tag;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.DyeColor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BlockShulkerBox extends BlockTransparent {

    public BlockShulkerBox() {
        this(DyeColor.PURPLE.getDyedData());
    }

    public BlockShulkerBox(int meta) {
        super(meta);
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public int getId() {
        return SHULKER_BOX;
    }

    @Override
    public String getName() {
        return this.getDyeColor().getName() + " Shulker Box";
    }

    @Override
    public double getHardness() {
        return 6;
    }

    @Override
    public double getResistance() {
        return 30;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        this.getLevel().setBlock(block, this, true, true);
        CompoundTag nbt = new CompoundTag("")
                .putString("id", BlockEntity.SHULKER_BOX)
                .putInt("x", (int) this.x)
                .putInt("y", (int) this.y)
                .putInt("z", (int) this.z);

        if (item.getNamedTag() != null && item.getNamedTag().contains("List")){
        	nbt.putList(item.getNamedTag().getList("Items"));
        }

        if (item.hasCustomName()) {
            nbt.putString("CustomName", item.getCustomName());
        }

        if (item.hasCustomBlockData()) {
            Map<String, Tag> customData = item.getCustomBlockData().getTags();
            for (Map.Entry<String, Tag> tag : customData.entrySet()) {
                nbt.put(tag.getKey(), tag.getValue());
            }
        }

        new BlockEntityShulkerBox(this.getLevel().getChunk((int) this.x >> 4, (int) this.z >> 4), nbt);
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        if (player != null) {
            Block top = up();
            if (!top.isTransparent()) {
                return true;
            }

            BlockEntity t = this.getLevel().getBlockEntity(this);
            BlockEntityShulkerBox shulkerBox;
            if (t instanceof BlockEntityShulkerBox) {
                shulkerBox = (BlockEntityShulkerBox) t;
            } else {
                CompoundTag nbt = new CompoundTag("")
                        .putString("id", BlockEntity.SHULKER_BOX)
                        .putInt("x", (int) this.x)
                        .putInt("y", (int) this.y)
                        .putInt("z", (int) this.z);
                shulkerBox = new BlockEntityShulkerBox(this.getLevel().getChunk((int) (this.x) >> 4, (int) (this.z) >> 4), nbt);
            }

            if (shulkerBox.namedTag.contains("Lock") && shulkerBox.namedTag.get("Lock") instanceof StringTag) {
                if (!shulkerBox.namedTag.getString("Lock").equals(item.getCustomName())) {
                    return true;
                }
            }

            player.addWindow(shulkerBox.getInventory());
        }

        return true;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[]{
                this.toItem()
        };
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public Item toItem() {
        Item item = Item.get(Item.SHULKER_BOX, this.meta);
        BlockEntity blockEntity = this.level.getBlockEntity(this);
        if (blockEntity instanceof BlockEntityShulkerBox) {
            item.setNamedTag(blockEntity.namedTag);
            List<String> lore = new ArrayList<>();
            int count = 0;
            Collection<Item> items = ((BlockEntityShulkerBox) blockEntity).getInventory().getContents().values();
            for (Item i : items) {
                lore.add(i.getName() + " x" + i.getCount());
                ++count;
                if (count == 5 && items.size() - count > 0) {
                    lore.add("and " + (items.size() - count) + " more...");
                    break;
                }
            }
            item.setLore(lore.stream().toArray(String[]::new));
        }
        return item;
    }

    public DyeColor getDyeColor() {
        return DyeColor.getByDyedData(this.meta);
    }

    @Override
    public BlockColor getColor() {
        return this.getDyeColor().getColor();
    }
}
