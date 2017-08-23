package cn.nukkit.network.protocol;

/**
 * @author Nukkit Project Team
 */
public class AnimatePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.ANIMATE_PACKET;

    public final int ACTION_NO_ACTION = 0;
    public final int ACTION_SWING = 1;
    public final int ACTION_WAKEUP = 3;
    public final int ACTION_CRITICAL_HIT = 4;
    public final int ACTION_MAGIC_CRITICAL_HIT = 5;
    public final int ACTION_ROW_RIGHT = 128; // -> for Boat?!
    public final int ACTION_ROW_LEFT = 129;  // ->

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

        switch (this.action) {
            case ACTION_ROW_RIGHT:
            case ACTION_ROW_LEFT:
                /** todo: do it right */
                this.putLFloat(0);
        }
    }

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

}
