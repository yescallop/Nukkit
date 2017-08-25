package cn.nukkit.network.protocol;

public class ServerSettingsRequestPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SERVER_SETTINGS_REQUEST_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        // Nothing there
    }

    @Override
    public void encode() {
        // Nothing there
    }
}
