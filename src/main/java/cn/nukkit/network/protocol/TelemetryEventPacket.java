package cn.nukkit.network.protocol;

public class TelemetryEventPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.TELEMETRY_EVENT_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long eid;
    public int unknown1;
    public byte[] unknown2;

    @Override
    public void decode() {
        this.eid = this.getVarLong();
        this.unknown1 = this.getVarInt();
        this.unknown2 = this.getByteArray();
    }

    @Override
    public void encode() {
        this.putVarLong(this.eid);
        this.putVarInt(this.unknown1);
        this.putByteArray(this.unknown2);
    }
}
