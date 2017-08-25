package cn.nukkit.network.protocol;

public class UpdateEquipmentPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.UPDATE_EQUIPMENT_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int windowId;
    public int windowType;
    public int unknown; //TODO: find out what this is (vanilla always sends 0)
    public long entityUniqueId;
    public byte[] namedtag;

    @Override
    public void decode() {
        this.windowId = this.getByte();
        this.windowType = this.getByte();
        this.entityUniqueId = this.getVarLong();
        this.namedtag = this.getByteArray();
    }

    @Override
    public void encode() {
        this.putByte((byte) this.windowId);
        this.putByte((byte) this.windowType);
        this.putVarLong(this.entityUniqueId);
        this.put(this.namedtag);
    }
}
