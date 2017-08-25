package cn.nukkit.network.protocol;

public class ShowProfilePacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SHOW_PROFILE_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String string1;

    @Override
    public void decode() {
        this.string1 = this.getString();
    }

    @Override
    public void encode() {
        this.putString(this.string1);
    }
}
