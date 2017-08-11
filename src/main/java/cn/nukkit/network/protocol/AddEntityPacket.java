package cn.nukkit.network.protocol;

import cn.nukkit.entity.Attribute;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.math.Vector3f;
import cn.nukkit.utils.Binary;
import cn.nukkit.utils.BinaryStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class AddEntityPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.ADD_ENTITY_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityUniqueId;
    public long entityRuntimeId;
    public int type;
    public float x;
    public float y;
    public float z;
    public float speedX = 0.0f;
    public float speedY = 0.0f;
    public float speedZ = 0.0f;
    public float yaw = 0.0f;
    public float pitch = 0.0f;
    public EntityMetadata metadata = new EntityMetadata();
    public Attribute[] attributes = new Attribute[0];
    public final Object[][] links = new Object[0][3];

    @Override
    public void decode() {
        this.entityUniqueId = this.getVarLong();
        this.entityRuntimeId = this.getUnsignedVarLong();
        this.type = (int) this.getUnsignedVarInt();
        Vector3f v = this.getVector3f();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        v.add(this.speedX, this.speedY, this.speedZ);
        this.pitch = this.getLFloat();
        this.yaw = this.getLFloat();
        this.attributes = this.getAttributeList();
        this.metadata = Binary.readMetadata(this.getByteArray()); // I'm not sure 100% if this is ok.

        int linkCount = (int) this.getUnsignedVarInt();
        for (int i = 0; i < linkCount; i++) {
            this.links[i] = this.getEntityLink();
        }
    }

    @Override
    public void encode() {
        this.reset();
        this.putVarLong(this.entityUniqueId);
        this.putUnsignedVarLong(this.entityRuntimeId);
        this.putUnsignedVarInt(this.type);
        this.putVector3f(this.x, this.y, this.z);
        this.putVector3f(this.speedX, this.speedY, this.speedZ);
        this.putLFloat(this.pitch);
        this.putLFloat(this.yaw);
        this.putAttributeList(this.attributes);
        this.put(Binary.writeMetadata(this.metadata));
        this.putUnsignedVarInt(this.links.length);
        for (Object[] link : this.links) {
            this.putVarLong((long) link[0]);
            this.putVarLong((long) link[1]);
            this.putByte((byte) link[2]);
            this.putByte((byte) link[3]);
        }
    }
}
