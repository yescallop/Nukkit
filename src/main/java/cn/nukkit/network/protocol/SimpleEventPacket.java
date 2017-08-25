package cn.nukkit.network.protocol;

public class SimpleEventPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.SIMPLE_EVENT_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int unknownShort1;

    @Override
    public void decode() {
        this.unknownShort1 = this.getLShort();
    }

    @Override
    public void encode() {
        this.putShort(this.unknownShort1);
    }
}
