package cn.nukkit.network.protocol;

public class SimpleEventPacket extends DataPacket {

    public short unknown;

    @Override
    public byte pid() {
        return ProtocolInfo.SIMPLE_EVENT_PACKET;
    }

    @Override
    public void decode() {
        this.unknown = (short) this.getLShort();

    }

    @Override
    public void encode() {
        this.putShort(this.unknown);
    }
}
