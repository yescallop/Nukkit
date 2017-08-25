package cn.nukkit.network.protocol;

import cn.nukkit.entity.data.Skin;

import java.util.UUID;

/**
 * @author Nukkit Project Team
 */
public class PlayerListPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.PLAYER_LIST_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public static final byte TYPE_ADD = 0;
    public static final byte TYPE_REMOVE = 1;

    public Entry[] entries = new Entry[0];
    public int type;

    @Override
    public void decode() {
        this.type = this.getByte();
        int count = (int) this.getUnsignedVarInt();
        for (int i = 0; i < count; ++i) {
            for (Entry entry : this.entries) {
                if (this.type == TYPE_ADD) {
                    entry.uuid = this.getUUID();
                    entry.entityUniqueId = this.getVarLong();
                    entry.username = this.getString();
                    entry.skinId = this.getString();
                    entry.skinData = this.getString();
                    entry.capeData = this.getString();
                    entry.geometryModel = this.getString();
                    entry.xboxUserId = this.getString();
                } else {
                    entry.uuid = this.getUUID();
                }
                this.entries[i] = entry;
            }
        }
    }

    @Override
    public void encode() {
        this.reset();
        this.putByte((byte) this.type);
        this.putUnsignedVarInt(this.entries.length);
        for (Entry entry : this.entries) {
            if (type == TYPE_ADD) {
                this.putUUID(entry.uuid);
                this.putVarLong(entry.entityUniqueId);
                this.putString(entry.username);
                this.putString(entry.skinId);
                this.putString(entry.skinData);
                this.putString(entry.capeData);
                this.putString(entry.geometryModel);
                this.putString(entry.xboxUserId);
            } else {
                this.putUUID(entry.uuid);
            }
        }

    }

    public static class Entry {

        public UUID uuid;
        public long entityUniqueId = 0;
        public String username = "";
        public String skinId;
        public String skinData;
        public String capeData;
        public String geometryModel;
        public String xboxUserId;

        public Entry(UUID uuid) {
            this.uuid = uuid;
        }

        public Entry(UUID uuid, long entityUniqueId, String username, String skinId, String skinData, String capeData, String geometryModel, String xboxUserId) {
            this.uuid = uuid;
            this.entityUniqueId = entityUniqueId;
            this.username = username;
            this.skinId = skinId;
            this.skinData = skinData;
            this.capeData = capeData;
            this.geometryModel = geometryModel;
            this.xboxUserId = xboxUserId;
        }
    }

}
