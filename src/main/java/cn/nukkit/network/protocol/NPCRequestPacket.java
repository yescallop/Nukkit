package cn.nukkit.network.protocol;

public class NPCRequestPacket extends DataPacket {

    public final static byte NETWORK_ID = ProtocolInfo.NPC_REQUEST_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long entityRuntimeId;
    public int requestType;
    public String commandString;
    public int actionType;

    @Override
    public void decode() {
        this.entityRuntimeId = this.getUnsignedVarLong();
        this.requestType = this.getByte();
        this.commandString = this.getString();
        this.actionType = this.getByte();
    }

    @Override
    public void encode() {
        this.putUnsignedVarLong(this.entityRuntimeId);
        this.putByte((byte) this.requestType);
        this.putString(this.commandString);
        this.putByte((byte) this.actionType);
    }
}
