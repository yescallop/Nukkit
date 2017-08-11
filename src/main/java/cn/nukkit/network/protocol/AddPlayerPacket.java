package cn.nukkit.network.protocol;

import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.item.Item;
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
    public float speedX;
    public float speedY;
    public float speedZ;
    public float pitch;
    public float yaw;
    public Item item;
    public EntityMetadata metadata = new EntityMetadata();
    
    public int uvarint1 = 0;
    public int uvarint2 = 0;
    public int uvarint3 = 0; 
    public int uvarint4 = 0;
    
    public int long1 = 0;

    @Override
    public void decode() {
        this.uuid = this.getUUID();
        this.username = this.getString();
        this.entityUniqueId = this.getVarLong();
        this.entityRuntimeId = this.getVarLong();
        //x,y,z
        //speed
        this.pitch = this.getLFloat();
        this.yaw = this.getLFloat();
        this.item = this.getSlot();
        //metadata
        
        this.uvarint1 = (int) this.getUnsignedVarInt();
        this.uvarint2 = (int) this.getUnsignedVarInt();
        this.uvarint3 = (int) this.getUnsignedVarInt();
        this.uvarint4 = (int) this.getUnsignedVarInt(); 
        
        this.long1 = (int) this.getLLong();
    }

    @Override
    public void encode() {
        this.reset();
        this.putUUID(this.uuid);
        this.putString(this.username);
        this.putVarLong(this.entityUniqueId);
        this.putVarLong(this.entityRuntimeId);
        this.putVector3f(this.x, this.y, this.z);
        this.putVector3f(this.speedX, this.speedY, this.speedZ);
        this.putLFloat(this.pitch);
        this.putLFloat(this.yaw); //TODO headrot
        this.putLFloat(this.yaw);
        this.putSlot(this.item);

        this.put(Binary.writeMetadata(this.metadata));
        
        this.putUnsignedVarInt(this.uvarint1);
	this.putUnsignedVarInt(this.uvarint2);
	this.putUnsignedVarInt(this.uvarint3);
	this.putUnsignedVarInt(this.uvarint4);
        
	this.putLLong(this.long1);
    }
}
