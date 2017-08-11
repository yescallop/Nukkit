package cn.nukkit.network.protocol;

import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3f;
import cn.nukkit.utils.Binary;

import java.util.UUID;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class AddPlayerPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.ADD_PLAYER_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public UUID uuid;
    public String username;
    public long entityUniqueId;
    public long entityRuntimeId;
    public float x;
    public float y;
    public float z;
    public float speedX = 0.0f;
    public float speedY = 0.0f;
    public float speedZ = 0.0f;
    public float pitch = 0.0f;
    public float yaw = 0.0f;
    public float headYaw;
    public Item item;
    public EntityMetadata metadata = new EntityMetadata();

    // TODO: Aventure settings stuff
    public int varint1 = 0;
    public int varint2 = 0;
    public int varint3 = 0;
    public int varint4 = 0;
    public long long1 = 0;
    public final Object[][] links = new Object[0][3];

    @Override
    public void decode() {
        this.uuid = this.getUUID();
        this.username = this.getString();
        this.entityUniqueId = this.getVarLong();
        this.entityRuntimeId = this.getUnsignedVarLong();
        Vector3f v = this.getVector3f();
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        v.add(this.speedX, this.speedY, this.speedZ);
        this.pitch = this.getLFloat();
        this.yaw = this.getLFloat();
        this.headYaw = this.getLFloat();
        this.item = this.getSlot();
        this.metadata = Binary.readMetadata(this.getByteArray());

        this.varint1 = (int) this.getUnsignedVarInt();
        this.varint2 = (int) this.getUnsignedVarInt();
        this.varint3 = (int) this.getUnsignedVarInt();
        this.varint4 = (int) this.getUnsignedVarInt();
        this.long1 = this.getLLong();

        int linkCount = (int) this.getUnsignedVarInt();
        for (int i = 0; i < linkCount; i++) {
            this.links[i] = this.getEntityLink();
        }
    }

    @Override
    public void encode() {
        this.reset();
        this.putUUID(this.uuid);
        this.putString(this.username);
        this.putVarLong(this.entityUniqueId);
        this.putUnsignedVarLong(this.entityRuntimeId);
        this.putVector3f(this.x, this.y, this.z);
        this.putVector3f(this.speedX, this.speedY, this.speedZ);
        this.putLFloat(this.pitch);
        this.putLFloat(this.headYaw);
        this.putLFloat(this.yaw);
        this.putSlot(this.item);
        this.put(Binary.writeMetadata(this.metadata));

        this.putUnsignedVarInt(this.varint1);
        this.putUnsignedVarInt(this.varint2);
        this.putUnsignedVarInt(this.varint3);
        this.putUnsignedVarInt(this.varint4);
        this.putLLong(this.long1);

        this.putUnsignedVarInt(this.links.length);
        for (Object[] link : this.links) {
            this.putVarLong((long) link[0]);
            this.putVarLong((long) link[1]);
            this.putByte((byte) link[2]);
            this.putByte((byte) link[3]);
        }
    }
}
