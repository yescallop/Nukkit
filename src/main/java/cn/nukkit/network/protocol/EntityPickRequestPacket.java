package cn.nukkit.network.protocol;

public class EntityPickRequestPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.ENTITY_PICK_REQUEST_PACKET;

    public long entityTypeId;
    public int hotbarSlot;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.entityTypeId = this.getLLong();
        this.hotbarSlot = this.getByte();
    }

    @Override
    public void encode() {
        this.putLLong(this.entityTypeId);
        this.putByte((byte) this.hotbarSlot);
    }
}
