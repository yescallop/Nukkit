package cn.nukkit.network.protocol;

public class SubClientLoginPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SUB_CLIENT_LOGIN_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String connectionRequestData;

    @Override
    public void decode() {
        this.connectionRequestData = this.getString();
    }

    @Override
    public void encode() {
        this.putString(this.connectionRequestData);
    }
}
