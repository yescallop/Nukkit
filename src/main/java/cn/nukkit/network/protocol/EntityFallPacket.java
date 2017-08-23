package cn.nukkit.network.protocol;

public class EntityFallPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.ENTITY_FALL_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityRuntimeId;
    public float fallDistance;
    public boolean unknown;

    @Override
    public void decode() {
        this.entityRuntimeId = this.getUnsignedVarLong();
        this.fallDistance = this.getLFloat();
        this.unknown = this.getBoolean();
    }

    @Override
    public void encode() {
        this.putUnsignedVarLong(this.entityRuntimeId);
        this.putLFloat(this.fallDistance);
        this.putBoolean(this.unknown);
    }
}
