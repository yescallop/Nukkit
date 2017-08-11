package cn.nukkit.network.protocol;

import cn.nukkit.math.BlockVector3;

/**
 * Created by NycuRO on 11.08.2017
 */

public class AddHangingEntityPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.ADD_HANGING_ENTITY_PACKET;

    public long entityUniqueId;
    public long entityRuntimeId;
    public int x;
    public int y;
    public int z;
    public int unknown; // TODO: Rotation
    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.entityUniqueId = this.getVarLong();
        this.entityRuntimeId = this.getUnsignedVarLong();
        BlockVector3 v = this.getBlockVector3();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.unknown = this.getVarInt();
    }

    @Override
    public void encode() {
        this.putVarLong(this.entityUniqueId);
        this.putUnsignedVarLong(this.entityRuntimeId);
        this.putBlockVector3(this.x, this.y, this.z);
        this.putVarInt(this.unknown);
    }
}
