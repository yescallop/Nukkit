package cn.nukkit.network.protocol;

/**
 * @author Nukkit Project Team
 */
public class AnimatePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.ANIMATE_PACKET;

    public final int ACTION_SWING_ARM = 1;

    public final int ACTION_STOP_SLEEP = 3;
    public final int ACTION_CRITICAL_HIT = 4;

    public int action;
    public long entityRuntimeId;
    public float float1 = 0.0f; // TODO (Boat rowing time?)

    @Override
    public void decode() {
        this.action = this.getVarInt();
        this.entityRuntimeId = getUnsignedVarLong();
        if ((this.action & 0x80) != 0) {
            this.float1 = this.getLFloat();
        }
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarInt(this.action);
        this.putUnsignedVarLong(this.entityRuntimeId);
        if ((this.action & 0x80) != 0) {
            this.putLFloat(this.float1);
        }
    }

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

}
