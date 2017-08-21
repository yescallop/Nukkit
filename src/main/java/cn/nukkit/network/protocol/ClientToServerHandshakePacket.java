package cn.nukkit.network.protocol;

public class ClientToServerHandshakePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.CLIENT_TO_SERVER_HANDSHAKE_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        // Nothing
    }

    @Override
    public void encode() {
        // Nothing
    }
}
