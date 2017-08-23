package cn.nukkit.network.protocol;

public class InitiateWebSocketConnectionPacket extends DataPacket {

    @Override
    public byte pid() {
        return ProtocolInfo.INITIATE_WEB_SOCKET_CONNECTION_PACKET;
    }

    @Override
    public void decode() {
        // TODO
    }

    @Override
    public void encode() {
        // TODO
    }
}
