package cn.nukkit.network.protocol;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class RemoveEntityPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.REMOVE_ENTITY_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityUniqueId;

    @Override
    public void decode() {
        this.entityUniqueId = this.getVarLong();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.entityUniqueId);
    }
}
