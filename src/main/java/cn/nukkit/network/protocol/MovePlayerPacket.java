package cn.nukkit.network.protocol;

import cn.nukkit.math.Vector3f;

/**
 * Created on 15-10-14.
 */
public class MovePlayerPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.MOVE_PLAYER_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public static final int MODE_NORMAL = 0;
    public static final int MODE_RESET = 1;
    public static final int MODE_TELEPORT = 2;
    public static final int MODE_PITCH = 3; //facepalm Mojang

    public long entityUniqueId;
    public float x;
    public float y;
    public float z;
    public float yaw;
    public float bodyYaw;
    public float pitch;
    public int mode = MODE_NORMAL;
    public boolean onGround = false; // TODO
    public long ridingEid = 0;
    public int int1 = 0;
    public int int2 = 0;

    @Override
    public void decode() {
        this.entityUniqueId = this.getVarLong();
        Vector3f v = this.getVector3f();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.pitch = this.getLFloat();
        this.bodyYaw = this.getLFloat();
        this.yaw = this.getLFloat();
        this.mode = this.getByte();
        this.onGround = this.getBoolean();
        this.ridingEid = this.getUnsignedVarLong();
        if (this.mode == MODE_TELEPORT){
            this.int1 = this.getLInt();
            this.int2 = this.getLInt();
        }
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.entityUniqueId);
        this.putVector3f(this.x, this.y, this.z);
        this.putLFloat(this.pitch);
        this.putLFloat(this.yaw);
        this.putLFloat(this.bodyYaw);
        this.putByte((byte) this.mode);
        this.putBoolean(this.onGround);
        this.putUnsignedVarLong(this.ridingEid);
        if (this.mode == MODE_TELEPORT){
            this.putLInt(this.int1);
            this.putLInt(this.int2);
        }
    }
}
