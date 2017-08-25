package cn.nukkit.network.protocol;

public class RiderJumpPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.RIDER_JUMP_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int unknown;

    @Override
    public void decode() {
        this.unknown = this.getVarInt();
    }

    @Override
    public void encode() {
        this.putVarInt(this.unknown);
    }
}
