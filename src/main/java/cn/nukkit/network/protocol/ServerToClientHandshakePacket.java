package cn.nukkit.network.protocol;

public class ServerToClientHandshakePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SERVER_TO_CLIENT_HANDSHAKE_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String publicKey;
    public String serverToken;
    public byte[] token;

    @Override
    public void decode() {
        this.publicKey = this.getString();
        this.serverToken = this.getString();
        this.token = this.getByteArray();
    }

    @Override
    public void encode() {
        this.putString(this.publicKey);
        this.putString(this.serverToken);
        this.putByteArray(this.token);
    }
}
