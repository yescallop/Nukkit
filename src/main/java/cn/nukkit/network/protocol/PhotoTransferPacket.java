package cn.nukkit.network.protocol;

public class PhotoTransferPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.PHOTO_TRANSFER_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public String string1;
    public String string2;
    public String string3;

    @Override
    public void decode() {
        this.string1 = this.getString();
        this.string2 = this.getString();
        this.string3 = this.getString();
    }

    @Override
    public void encode() {
        this.putString(this.string1);
        this.putString(this.string2);
        this.putString(this.string3);
    }
}
