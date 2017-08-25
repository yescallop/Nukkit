package cn.nukkit.network.protocol;

import java.util.UUID;

public class PlayerSkinPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.PLAYER_SKIN_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public UUID uuid;
    public String skinId;
    public String skinName;
    public String serializeName;
    public String skinData;
    public String capeData;
    public String geometryModel;
    public String geometryData;

    @Override
    public void decode() {
        this.uuid = this.getUUID();
        this.skinId = this.getString();
        this.skinName = this.getString();
        this.serializeName = this.getString();
        this.skinData = this.getString();
        this.capeData = this.getString();
        this.geometryModel = this.getString();
        this.geometryData = this.getString();
    }

    @Override
    public void encode() {
        this.putUUID(this.uuid);
        this.putString(this.skinId);
        this.putString(this.skinName);
        this.putString(this.serializeName);
        this.putString(this.skinData);
        this.putString(this.capeData);
        this.putString(this.geometryModel);
        this.putString(this.geometryData);
    }
}
