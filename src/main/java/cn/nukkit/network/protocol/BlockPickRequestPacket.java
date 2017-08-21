package cn.nukkit.network.protocol;

import cn.nukkit.math.BlockVector3;

public class BlockPickRequestPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.BLOCK_PICK_REQUEST_PACKET;

    public int tileX;
    public int tileY;
    public int tileZ;
    public boolean addUserData;
    public int hotbarSlot;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        BlockVector3 v = this.getBlockVector3();
        this.tileX = v.x;
        this.tileY = v.y;
        this.tileZ = v.z;
        this.addUserData = this.getBoolean();
        this.hotbarSlot = this.getByte();
    }

    @Override
    public void encode() {
        this.putBlockVector3(this.tileX, this.tileY, this.tileZ);
        this.putBoolean(this.addUserData);
        this.putByte((byte) this.hotbarSlot);
    }
}
