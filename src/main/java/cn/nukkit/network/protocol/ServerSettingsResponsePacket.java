package cn.nukkit.network.protocol;

public class ServerSettingsResponsePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SERVER_SETTINGS_RESPONSE_PACKET;

    public int formId;
    public String formData; // JSON

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.formId = (int) this.getUnsignedVarInt();
        this.formData = this.getString();
    }

    @Override
    public void encode() {
        this.putUnsignedVarInt(this.formId);
        this.putString(this.formData);
    }
}
