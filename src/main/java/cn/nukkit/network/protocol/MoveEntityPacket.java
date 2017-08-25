package cn.nukkit.network.protocol;

import cn.nukkit.math.Vector3f;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class MoveEntityPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.MOVE_ENTITY_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityRuntimeId;
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float headYaw;
    public float pitch;
    public boolean onGround = false;
    public boolean teleport = false;

    @Override
    public void decode() {
        this.entityRuntimeId = this.getVarLong();
        Vector3f v = this.getVector3f();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.pitch = (float) (this.getByte() * (360D / 256D));
        this.headYaw = (float) (this.getByte() * (360D / 256D));
        this.yaw = (float) (this.getByte() * (360D / 256D));
        this.onGround = this.getBoolean();
        this.teleport = this.getBoolean();
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.entityRuntimeId);
        this.putVector3f((float) this.x, (float) this.y, (float) this.z);
        this.putByte((byte) (this.pitch / (360D / 256D)));
        this.putByte((byte) (this.headYaw / (360D / 256D)));
        this.putByte((byte) (this.yaw / (360D / 256D)));
        this.putBoolean(this.onGround);
        this.putBoolean(this.teleport);
    }
}
