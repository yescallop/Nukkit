package cn.nukkit.block;

import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.EntityDamageByBlockEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;

/**
 * @author PikyCZ
 */
public class BlockMagma extends BlockSolid {

    public BlockMagma(int meta) {
        super(meta);
    }

    public BlockMagma() {
        this(0);
    }

    @Override
    public String getName() {
        return "Magma Block";
    }

    @Override
    public int getId() {
        return MAGMA_BLOCK;
    }

    @Override
    public double getHardness() {
        return 0.5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public int getLightLevel() {
        return 3;
    }

    @Override
    public boolean hasEntityCollision() {
        return true;
    }

    public void onEntityCollide(Entity entity, EntityDamageByBlockEvent ev) {
        if (!entity.isSneaking()) {
            ev = new EntityDamageByBlockEvent(this, entity, DamageCause.FIRE, 1);
            entity.attack(ev);
        }
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= ItemTool.TIER_WOODEN) {
            return new Item[]{
                toItem()
            };
        } else {
            return new Item[0];
        }
    }

}
