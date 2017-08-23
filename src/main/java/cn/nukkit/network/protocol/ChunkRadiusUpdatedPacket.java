package cn.nukkit.network.protocol;

/**
 * author: MagicDroidX
 * Nukkit Project
 * Note: From SteadFast2 (SignedVarInt), in PM-MP (VarInt).
 */
public class ChunkRadiusUpdatedPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.CHUNK_RADIUS_UPDATED_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int radius;

    @Override
    public void decode() {
        this.radius = this.getSignedVarInt();
    }

    @Override
    public void encode() {
        super.reset();
        this.putSignedVarInt(this.radius);
    }
}
