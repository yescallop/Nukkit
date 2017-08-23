package cn.nukkit.network.protocol;

public class CommandOutputPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.COMMAND_OUTPUT_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
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
