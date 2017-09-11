package cn.nukkit.block;

import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;

/**
 * @author Sandertv
 */
public class BlockWallBanner extends BlockStandingBanner {

    public BlockWallBanner() {
        this(0);
    }

    public BlockWallBanner(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WALL_BANNER;
    }

    @Override
    public String getName() {
        return "Wall Banner";
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (this.meta >= BlockFace.NORTH.getIndex() && this.meta <= BlockFace.EAST.getIndex()) {
                if (this.getSide(BlockFace.fromIndex(this.meta).getOpposite()).getId() == AIR) {
                    this.getLevel().useBreakOn(this);
                }
                return Level.BLOCK_UPDATE_NORMAL;
            }
        }
        return 0;
    }
}