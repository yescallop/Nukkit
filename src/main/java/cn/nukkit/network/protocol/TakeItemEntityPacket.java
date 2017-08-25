package cn.nukkit.network.protocol;

/**
 * Created on 15-10-14.
 */
public class TakeItemEntityPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.TAKE_ITEM_ENTITY_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long target;
    public long entityId;

    @Override
    public void decode() {
        this.target = this.getVarLong();
        this.entityId = this.getVarLong();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.target);
        this.putVarLong(this.entityId);
    }
}
