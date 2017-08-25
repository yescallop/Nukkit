package cn.nukkit.network.protocol;

import cn.nukkit.math.Vector3f;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class SetEntityMotionPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SET_ENTITY_MOTION_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityRuntimeId;
    public float motionX;
    public float motionY;
    public float motionZ;

    @Override
    public void decode() {
        this.entityRuntimeId = this.getUnsignedVarLong();
        Vector3f v = this.getVector3f();
        v.add(this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public void encode() {
        this.reset();
        this.putUnsignedVarLong(this.entityRuntimeId);
        this.putVector3f(this.motionX, this.motionY, this.motionZ);
    }
}
